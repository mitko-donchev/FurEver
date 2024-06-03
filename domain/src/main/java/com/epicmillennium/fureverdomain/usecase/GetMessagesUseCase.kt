package com.epicmillennium.fureverdomain.usecase

import com.epicmillennium.fureverdomain.message.Message
import com.epicmillennium.fureverdomain.message.MessageRepository
import kotlinx.coroutines.flow.Flow

class GetMessagesUseCase(private val messageRepository: MessageRepository) {

    operator fun invoke(matchId: String): Flow<List<Message>> =
        messageRepository.getMessages(matchId)
}