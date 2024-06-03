package com.epicmillennium.foreverdata.source.mock

import android.content.Context
import android.net.Uri
import com.apiguave.tinderclonedata.source.mock.extension.resourceUri
import com.epicmillennium.foreverdata.R
import com.epicmillennium.foreverdata.repository.picture.PictureRemoteDataSource
import kotlinx.coroutines.delay
import kotlin.random.Random

class PictureRemoteDataSourceMockImpl(private val context: Context) : PictureRemoteDataSource {
    override suspend fun addPictures(localPictures: List<Uri>): List<String> {
        delay(1000)
        return localPictures.mapIndexed { _, i ->
            "picture$i.jpg"
        }
    }

    override suspend fun addPicture(localPicture: Uri): String {
        delay(1000)
        return "picture1.jpg"
    }

    override suspend fun deletePicture(pictureName: String) {
        delay(500)
    }

    override suspend fun deletePictures(pictureNames: List<String>) {
        delay(500)
    }

    override suspend fun getPicture(userId: String, pictureName: String): Uri {
        //The random delayed is used to showcase the picture asynchronous loading
        delay(Random.nextLong(1000, 4000))
        return when (pictureName) {
            "dog_1.jpg" -> context.resourceUri(R.drawable.dog_1)
            "dog_2.jpg" -> context.resourceUri(R.drawable.dog_2)
            "dog_3.jpg" -> context.resourceUri(R.drawable.dog_3)
            "dog_4.jpg" -> context.resourceUri(R.drawable.dog_4)
            "dog_5.jpg" -> context.resourceUri(R.drawable.dog_5)
            "dog_6.jpg" -> context.resourceUri(R.drawable.dog_6)
            "dog_7.jpg" -> context.resourceUri(R.drawable.dog_7)
            "dog_8.jpg" -> context.resourceUri(R.drawable.dog_8)
            "dog_9.jpg" -> context.resourceUri(R.drawable.dog_9)
            "dog_10.jpg" -> context.resourceUri(R.drawable.dog_10)
            "dog_11.jpg" -> context.resourceUri(R.drawable.dog_11)
            "dog_12.jpg" -> context.resourceUri(R.drawable.dog_12)
            else -> context.resourceUri(R.drawable.dog_1)
        }
    }
}