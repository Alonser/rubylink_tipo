package com.example.rubylink.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.*
import com.example.rubylink.ui.screen.ChatScreen
import com.example.rubylink.ui.screen.ChatListScreen
import com.example.rubylink.ui.screen.LoginScreen
import com.example.rubylink.ui.viewmodel.ChatViewModel

@Composable
fun AppNavGraph() {

    val navController = rememberNavController()
    val chatVM: ChatViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "login" // 🔥 ВАЖНО
    ) {

        // 🔐 ЛОГИН
        composable("login") {
            LoginScreen {
                navController.navigate("list") {
                    popUpTo("login") { inclusive = true }
                }
            }
        }

        // 💬 СПИСОК ЧАТОВ
        composable("list") {
            ChatListScreen { chatId ->
                navController.navigate("chat/$chatId")
            }
        }

        // 📩 ЧАТ
        composable("chat/{chatId}") {
            val chatId = it.arguments?.getString("chatId") ?: ""

            ChatScreen(
                chatViewModel = chatVM,
                chatId = chatId
            )
        }
    }
}