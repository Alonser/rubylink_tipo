package com.example.rubylink.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.*
import com.example.rubylink.ui.screen.ChatListScreen
import com.example.rubylink.ui.screen.ChatScreen
import com.example.rubylink.ui.viewmodel.ChatViewModel

object Routes {
    const val CHAT_LIST = "chat_list"
    const val CHAT = "chat"
}

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()
    val viewModel: ChatViewModel = viewModel()

    NavHost(navController, startDestination = Routes.CHAT_LIST) {

        composable(Routes.CHAT_LIST) {
            ChatListScreen(
                viewModel = viewModel,
                onChatClick = { chatId, userName ->
                    navController.navigate("${Routes.CHAT}/$chatId/$userName")
                }
            )
        }

        composable("${Routes.CHAT}/{chatId}/{userName}") {
            val chatId = it.arguments?.getString("chatId") ?: ""
            val userName = it.arguments?.getString("userName") ?: ""

            ChatScreen(
                viewModel = viewModel,
                chatId = chatId,
                userName = userName,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}