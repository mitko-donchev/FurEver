package com.epicmillennium.foreverdata.repository.message

import com.epicmillennium.fureverdomain.message.Message
import kotlinx.coroutines.flow.Flow

interface MessageRemoteDataSource {
    fun getMessages(matchId: String): Flow<List<Message>>
    suspend fun sendMessage(matchId: String, text: String)
}