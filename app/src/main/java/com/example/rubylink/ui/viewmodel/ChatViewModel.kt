package com.example.rubylink.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.rubylink.data.model.Message
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.UUID

class ChatViewModel : ViewModel() {

    private val db = FirebaseFirestore.getInstance()

    private val _messages = MutableStateFlow<List<Message>>(emptyList())
    val messages: StateFlow<List<Message>> = _messages

    fun listenMessages(chatId: String) {
        db.collection("chats")
            .document(chatId)
            .collection("messages")
            .orderBy("timestamp")
            .addSnapshotListener { snapshot, _ ->
                if (snapshot != null) {
                    _messages.value = snapshot.documents.mapNotNull {
                        it.toObject(Message::class.java)
                    }
                }
            }
    }

    fun sendMessage(text: String, chatId: String, senderId: String) {
        val message = Message(
            id = UUID.randomUUID().toString(),
            text = text,
            senderId = senderId,
            timestamp = System.currentTimeMillis()
        )

        db.collection("chats")
            .document(chatId)
            .collection("messages")
            .document(message.id)
            .set(message)
    }
}