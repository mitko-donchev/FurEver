package com.epicmillennium.fureverdomain.picture

sealed class Picture(val uri: String)

//A picture retrieved from the device
class LocalPicture(uri: String): Picture(uri)

//A picture retrieved from Firebase
class RemotePicture(uri: String, val filename: String): Picture(uri)
