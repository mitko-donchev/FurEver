package com.epicmillennium.furever.utils

import com.epicmillennium.furever.R

fun getImageResource(index: Int): Int = getListOfImages()[index]

fun getListOfImages(): List<Int> = listOf(
    R.drawable.dog_1, R.drawable.dog_2, R.drawable.dog_3, R.drawable.dog_4,
    R.drawable.dog_5, R.drawable.dog_6, R.drawable.dog_7, R.drawable.dog_8,
    R.drawable.dog_9, R.drawable.dog_10, R.drawable.dog_11, R.drawable.dog_12,
    R.drawable.dog_13, R.drawable.dog_14, R.drawable.dog_15, R.drawable.dog_16,
    R.drawable.dog_17, R.drawable.dog_18, R.drawable.dog_19, R.drawable.dog_20
)