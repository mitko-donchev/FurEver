package com.epicmillennium.fureverdomain.usecase

import com.epicmillennium.fureverdomain.message.MessageRepository

class SendMessageUseCase(private val messageRepository: MessageRepository) {

    suspend operator fun invoke(adoptionId: String, message: String): Result<Unit> =
        Result.runCatching { messageRepository.addMessage(adoptionId, message) }
}