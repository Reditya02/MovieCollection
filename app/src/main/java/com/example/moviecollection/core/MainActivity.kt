package com.example.moviecollection.core

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import com.example.ui.MovieCollectionApp
import com.example.ui.theme.MovieCollectionTheme
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                return@OnCompleteListener
            }
            val token = task.result
            Log.d("Reditya", "Token $token")
        })

        setContent {
            MovieCollectionTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    if (Build.VERSION.SDK_INT >= 33) {
                        val notificationPermission = rememberPermissionState(
                            permission = android.Manifest.permission.POST_NOTIFICATIONS
                        )
                        if (!notificationPermission.status.isGranted) {
                            LaunchedEffect(key1 = Unit, block = {
                                notificationPermission.launchPermissionRequest()
                            })
                        } else {
                            MovieCollectionApp()
                        }
                    } else {
                        MovieCollectionApp()
                    }
                }
            }
        }
    }
}