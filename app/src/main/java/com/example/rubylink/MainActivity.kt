package com.example.rubylink
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.rubylink.ui.chat.ChatApp
import com.example.rubylink.ui.theme.RubyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RubyTheme {
                ChatApp()
            }
        }
    }
}