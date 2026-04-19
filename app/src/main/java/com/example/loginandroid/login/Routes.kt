package com.example.loginandroid.login

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed class Routes: NavKey {
    @Serializable
    data object Categories : Routes()

    @Serializable
    data object Products : Routes()
}

