package com.example.rubylink
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.rubylink.ui.screen.ChatScreen
import com.example.rubylink.ui.theme.RubyTheme
import com.example.rubylink.ui.viewmodel.ChatViewModel

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

@Composable
fun ChatApp() {
    val viewModel: ChatViewModel = viewModel()  // Правильный способ
    ChatScreen(viewModel = viewModel)
}