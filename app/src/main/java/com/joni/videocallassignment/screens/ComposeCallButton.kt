/*
package com.joni.videocallassignment.screens



import android.content.Context
import android.view.ViewGroup
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView

import com.zegocloud.uikit.prebuilt.call.invite.widget.ZegoSendCallInvitationButton


@Composable
fun ComposeCallButton(
    modifier: Modifier = Modifier,
    targetUserId: String,
    targetUserName: String,
    isVideoCall: Boolean = true,
    onButtonCreated: (ZegoSendCallInvitationButton) -> Unit = {}
) {
    val context = LocalContext.current

    AndroidView(
        factory = { ctx ->
            ZegoCallManager.createCallButton(ctx, targetUserId, targetUserName).apply {
                setIsVideoCall(isVideoCall)
                onButtonCreated(this)
            }
        },
        modifier = modifier,
        update = { button ->
            // Update button properties if needed
            button.setIsVideoCall(isVideoCall)
        }
    )
}

// Alternative: Custom Composable Call Button
@Composable
fun CallNowButton(
    targetUserId: String,
    targetUserName: String,
    onClick: () -> Unit = {},
    isEnabled: Boolean = true
) {
    val context = LocalContext.current
    var callButton by remember { mutableStateOf<ZegoSendCallInvitationButton?>(null) }

    // Create a transparent container for the button
    AndroidView(
        factory = { ctx ->
            // Create a container view
            android.widget.LinearLayout(ctx).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                orientation = android.widget.LinearLayout.VERTICAL

                // Create the actual call button but hide it
                val button = ZegoCallManager.createCallButton(ctx, targetUserId, targetUserName)
                button.visibility = android.view.View.GONE
                addView(button)
                callButton = button
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
    )

    // Custom styled button that triggers the hidden call button
    Button(
        onClick = {
            onClick()
            callButton?.performClick()
        },
        modifier = Modifier.fillMaxWidth(),
        enabled = isEnabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary
        )
    ) {
        Icon(
            imageVector = Icons.Default.Call,
            contentDescription = "Video Call"
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text("Call Now")
    }
}*/
