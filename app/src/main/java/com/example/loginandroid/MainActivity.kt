package com.example.loginandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.loginandroid.ui.theme.LoginAndroidTheme
import android.util.Log
import com.example.loginandroid.login.LoginWrapper

class MainActivity : ComponentActivity() {
    // TAG de la aplicacion

    private val TAG = "MainActivityLog"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.e(TAG, "Aplicacion principal creada")

        enableEdgeToEdge()
        setContent {
            LoginAndroidTheme {
                LoginWrapper()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.e(TAG, "Aplicacion principal iniciada")
    }

    override fun onResume() {
        super.onResume()
        Log.e(TAG, "Aplicacion principal en primer plano")
    }

    override fun onPause() {
        super.onPause()
        Log.e(TAG, "Aplicacion principal en segundo plano")
    }

    override fun onRestart() {
        super.onRestart()
        Log.e(TAG, "Aplicacion principal reiniciada")
    }

    override fun onStop() {
        super.onStop()
        Log.e(TAG, "Aplicacion principal parada")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG, "Aplicacion principal destruida")
    }


}