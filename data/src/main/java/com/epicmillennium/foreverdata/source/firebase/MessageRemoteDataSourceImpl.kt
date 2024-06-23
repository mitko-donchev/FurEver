package com.epicmillennium.foreverdata.source.firebase

import com.apiguave.tinderclonedata.source.firebase.MessageApi
import com.epicmillennium.foreverdata.repository.message.MessageRemoteDataSource
import com.epicmillennium.fureverdomain.message.Message
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MessageRemoteDataSourceImpl : MessageRemoteDataSource {

    override fun getMessages(matchId: String): Flow<List<Message>> =
        MessageApi.getMessages(matchId).map { list ->
            list.map {
                val isSender = it.senderId == AuthApi.userId!!
                val text = it.message
                Message(text, isSender)
            }
        }

    override suspend fun sendMessage(matchId: String, text: String) {
        MessageApi.sendMessage(matchId, text)
    }
}