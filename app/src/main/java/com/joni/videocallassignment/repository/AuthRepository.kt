package com.joni.videocallassignment.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

import com.joni.videocallassignment.models.AppUser
import com.joni.videocallassignment.models.UserRole
import com.zegocloud.uikit.prebuilt.call.ZegoUIKitPrebuiltCallService
import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationConfig
import im.zego.uikit.libuikitreport.CommonUtils.getApplication
import kotlinx.coroutines.tasks.await

class AuthRepository {

    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    suspend fun signUp(
        email: String,
        password: String,
        role: UserRole
    ) {
        val result = auth.createUserWithEmailAndPassword(email, password).await()
        val uid = result.user!!.uid

        val user = AppUser(
            uid = uid,
            email = email,
            role = role
        )

        db.collection("users")
            .document(uid)
            .set(user)
            .await()
    }

    suspend fun login(email: String, password: String): UserRole {
        val result = auth.signInWithEmailAndPassword(email, password).await()
        val uid = result.user!!.uid

        val doc = db.collection("users")
            .document(uid)
            .get()
            .await()
        val callInvitationConfig = ZegoUIKitPrebuiltCallInvitationConfig()
        ZegoUIKitPrebuiltCallService.init(getApplication(), 1478493845, "4ff5149f771aac5a7faa71a85710d07978abd366021dff0a9a7e11b8478f7297f", uid, uid,callInvitationConfig);
        return UserRole.valueOf(doc.getString("role")!!)
    }

    suspend fun getAllUsers(): List<AppUser> {
        return db.collection("users")
            .get()
            .await()
            .documents
            .mapNotNull { doc ->
                val role = doc.getString("role") ?: return@mapNotNull null
                AppUser(
                    uid = doc.getString("uid") ?: "",
                    email = doc.getString("email") ?: "",
                    role = UserRole.valueOf(role)
                )
            }
    }
}
