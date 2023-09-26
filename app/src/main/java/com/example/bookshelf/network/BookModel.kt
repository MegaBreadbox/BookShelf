package com.example.bookshelf.network

import kotlinx.serialization.Serializable

@Serializable
data class Bookshelf (
    val kind : String,
    val totalItems : Int,
    val items : Array<BookModel>
)
@Serializable
data class BookModel(
    val kind : String,
    val id : String,
    val etag : String,
    val selfLink : String,
    val volumeInfo : VolumeInfo
)

@Serializable
data class VolumeInfo(
    val title : String,
    val authors : Array<String>

)

