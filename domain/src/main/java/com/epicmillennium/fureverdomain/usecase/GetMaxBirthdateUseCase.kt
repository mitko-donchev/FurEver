package com.epicmillennium.fureverdomain.usecase

import java.time.LocalDate

class GetMaxBirthdateUseCase {

    operator fun invoke(): LocalDate = LocalDate.now().minusYears(18L)
}