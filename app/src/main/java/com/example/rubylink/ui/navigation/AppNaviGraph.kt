package com.example.rubylink.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.rubylink.ui.screen.ChatListScreen
import com.example.rubylink.ui.screen.ChatScreen

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "chat_list"
    ) {
        composable("chat_list") {
            ChatListScreen(
                onChatClick = { chatId, chatName ->
                    navController.navigate("chat/$chatId/$chatName")
                }
            )
        }

        composable(
            route = "chat/{chatId}/{chatName}",
            arguments = listOf(
                navArgument("chatId") { type = NavType.StringType },
                navArgument("chatName") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val chatId = backStackEntry.arguments?.getString("chatId") ?: ""
            val chatName = backStackEntry.arguments?.getString("chatName") ?: ""

            ChatScreen(
                chatId = chatId,
                chatName = chatName,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}