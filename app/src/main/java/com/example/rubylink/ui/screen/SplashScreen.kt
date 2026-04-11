package com.example.rubylink.ui.screen

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import com.example.rubylink.R

@Composable
fun SplashScreen(onFinish: () -> Unit) {

    LaunchedEffect(Unit) {
        kotlinx.coroutines.delay(1200)
        onFinish()
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ruby_logo),
            contentDescription = null,
            modifier = Modifier.size(140.dp)
        )
    }
}
