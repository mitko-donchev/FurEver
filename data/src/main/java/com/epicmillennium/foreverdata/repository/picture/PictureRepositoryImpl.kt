package com.epicmillennium.foreverdata.repository.picture

import android.net.Uri
import com.epicmillennium.fureverdomain.picture.PictureRepository

class PictureRepositoryImpl(private val pictureRemoteDataSource: PictureRemoteDataSource) :
    PictureRepository {
    override suspend fun addPictures(localPictures: List<String>): List<String> =
        pictureRemoteDataSource.addPictures(localPictures.map { Uri.parse(it) })

    override suspend fun addPicture(localPicture: String): String =
        pictureRemoteDataSource.addPicture(Uri.parse(localPicture))

    override suspend fun getPicture(userId: String, pictureName: String): String =
        pictureRemoteDataSource.getPicture(userId, pictureName).toString()

    override suspend fun deletePicture(pictureName: String) {
        pictureRemoteDataSource.deletePicture(pictureName)
    }

    override suspend fun deletePictures(pictureNames: List<String>) {
        pictureRemoteDataSource.deletePictures(pictureNames)
    }
}