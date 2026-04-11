package com.example.rubylink.ui.navigation


import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.rubylink.ui.screen.ChatListScreen
import com.example.rubylink.ui.screen.ChatScreen
import com.example.rubylink.ui.viewmodel.ChatViewModel
object Routes {
    const val CHAT_LIST = "chat_list"
    const val CHAT = "chat"
}

@Composable
fun AppNavGraph(
    navController: NavHostController = rememberNavController()
) {
    val viewModel: ChatViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = Routes.CHAT_LIST
    ) {

        composable(Routes.CHAT_LIST) {

            ChatListScreen(
                onChatClick = { chatId ->
                    navController.navigate("${Routes.CHAT}/$chatId")
                }
            )
        }

        composable("${Routes.CHAT}/{chatId}") { backStackEntry ->

            val chatId = backStackEntry.arguments?.getString("chatId") ?: ""

            ChatScreen(
                viewModel = viewModel,
                chatId = chatId,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}