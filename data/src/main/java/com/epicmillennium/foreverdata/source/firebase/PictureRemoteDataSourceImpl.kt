package com.epicmillennium.foreverdata.source.firebase

import android.net.Uri
import com.epicmillennium.foreverdata.repository.picture.PictureRemoteDataSource

class PictureRemoteDataSourceImpl : PictureRemoteDataSource {

    override suspend fun addPictures(localPictures: List<Uri>): List<String> =
        PictureApi.addPictures(localPictures)

    override suspend fun addPicture(localPicture: Uri): String = PictureApi.addPicture(localPicture)

    override suspend fun deletePicture(pictureName: String) = PictureApi.deletePicture(pictureName)

    override suspend fun deletePictures(pictureNames: List<String>) {
        PictureApi.deletePictures(pictureNames)
    }

    override suspend fun getPicture(userId: String, pictureName: String): Uri =
        PictureApi.getPictureUrl(userId, pictureName)
}