package com.epicmillennium.foreverdata.source.firebase.extension

import com.google.firebase.Timestamp
import java.time.LocalDate
import java.time.ZoneId
import java.util.Date

fun LocalDate.toTimestamp(): Timestamp =
    Timestamp(Date.from(this.atStartOfDay(ZoneId.systemDefault()).toInstant()))