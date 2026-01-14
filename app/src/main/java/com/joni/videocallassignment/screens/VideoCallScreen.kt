/*


package com.joni.videocallassignment.screens


import android.Manifest
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView

import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import im.zego.zego_express_engine.ZegoExpressEngine
import im.zego.zego_express_engine.ZegoTextureView

@OptIn(ExperimentalPermissionsApi::class, ExperimentalMaterial3Api::class)
@Composable
fun VideoCallScreen(
    callRoomId: String,
    userId: String,
    userName: String,
    otherUserName: String,
    onCallEnd: () -> Unit
) {
    val context = LocalContext.current
    val videoCallService = remember { ZegoVideoCallService.getInstance(context) }

    // Request camera and microphone permissions
    val permissionsState = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO
        )
    )

    var isMicMuted by remember { mutableStateOf(false) }
    var isVideoOff by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        if (permissionsState.allPermissionsGranted) {
            videoCallService.startVideoCall(callRoomId, userId, userName)
        } else {
            permissionsState.launchMultiplePermissionRequest()
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Video Call") },
                subtitle = { Text("With $otherUserName") }
            )
        },
        bottomBar = {
            CallControls(
                isMicMuted = isMicMuted,
                isVideoOff = isVideoOff,
                onMicToggle = {
                    isMicMuted = !isMicMuted
                    ZegoExpressEngine.getEngine()?.enableMic(!isMicMuted)
                },
                onVideoToggle = {
                    isVideoOff = !isVideoOff
                    ZegoExpressEngine.getEngine()?.enableCamera(!isVideoOff)
                },
                onEndCall = {
                    videoCallService.endCall()
                    onCallEnd()
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.surface)
        ) {
            if (permissionsState.allPermissionsGranted) {
                // Local Video Preview
                AndroidView(
                    factory = { context ->
                        ZegoTextureView(context).apply {
                            ZegoExpressEngine.getEngine()?.startPreview(this)
                        }
                    },
                    modifier = Modifier.fillMaxSize()
                )
            } else {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text("Permissions required for video call")
                    Button(
                        onClick = { permissionsState.launchMultiplePermissionRequest() },
                        modifier = Modifier.padding(top = 16.dp)
                    ) {
                        Text("Grant Permissions")
                    }
                }
            }
        }
    }
}

@Composable
fun CallControls(
    isMicMuted: Boolean,
    isVideoOff: Boolean,
    onMicToggle: () -> Unit,
    onVideoToggle: () -> Unit,
    onEndCall: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Mic Toggle
        FloatingActionButton(
            onClick = onMicToggle,
            containerColor = if (isMicMuted) MaterialTheme.colorScheme.errorContainer
            else MaterialTheme.colorScheme.primaryContainer
        ) {
            Icon(
                if (isMicMuted) Icons.Default.MicOff else Icons.Default.Mic,
                contentDescription = "Microphone"
            )
        }

        // Video Toggle
        FloatingActionButton(
            onClick = onVideoToggle,
            containerColor = if (isVideoOff) MaterialTheme.colorScheme.errorContainer
            else MaterialTheme.colorScheme.primaryContainer
        ) {
            Icon(
                if (isVideoOff) Icons.Default.VideocamOff else Icons.Default.Videocam,
                contentDescription = "Camera"
            )
        }

        // End Call
        FloatingActionButton(
            onClick = onEndCall,
            containerColor = MaterialTheme.colorScheme.errorContainer
        ) {
            Icon(Icons.Default.CallEnd, contentDescription = "End Call")
        }
    }
}

*/
