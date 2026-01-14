/*



import android.app.Application
import android.content.Context
import com.zegocloud.uikit.prebuilt.call.ZegoUIKitPrebuiltCallService
import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationConfig
import com.zegocloud.uikit.prebuilt.call.invite.widget.ZegoSendCallInvitationButton
import com.zegocloud.uikit.service.defines.ZegoUIKitUser


class ZegoCallManager {

    companion object {
        // Replace with your ZegoCloud credentials
        const val APP_ID: Long = 1478493845// Get from ZegoCloud dashboard
        const val APP_SIGN: String = "4ff5149f771aac5a7faa71a85710d07978abd366021dff0a9a7e11b8478f7297" // Get from ZegoCloud dashboard

        val callInvitationConfig = ZegoUIKitPrebuiltCallInvitationConfig()


        fun initializeZegoUIKit(context: Application, userId: String, userName: String) {
            // This should be called once when the user logs in
           ZegoUIKitPrebuiltCallService.init(
                context,
                APP_ID,
                APP_SIGN,
                userId,
                userName,
               callInvitationConfig
            )
        }

        fun createCallButton(
            context: Context,
            targetUserId: String,
            targetUserName: String
        ): ZegoSendCallInvitationButton {
            return ZegoSendCallInvitationButton(context).apply {
                setIsVideoCall(true)
                setResourceID("zego_uikit_call") // Make sure this matches your ZegoCloud console

                // Create a list of invitees
                val invitees = listOf(
                    ZegoUIKitUser(targetUserId, targetUserName)
                )
                setInvitees(invitees)

                // Optional: Set custom call ID
                // setCallID("custom_call_id")

                // Optional: Set timeout (default is 60 seconds)
                // setTimeout(60)

                // Optional: Set custom data
                // val customData = mapOf("appointment_id" to "12345")
                // setData(customData)
            }
        }

        fun cleanup() {
           ZegoUIKitPrebuiltCallService.unInit()
        }
    }
}*/
