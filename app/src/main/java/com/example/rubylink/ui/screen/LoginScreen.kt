package com.example.rubylink.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LoginScreen(onSuccess: () -> Unit) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally // 🔥 ВАЖНО
    ) {

        Button(
            onClick = { onSuccess() },
            modifier = Modifier
                .fillMaxWidth(0.6f) // 🔥 красиво по центру
        ) {
            Text("Войти")
        }
    }
}