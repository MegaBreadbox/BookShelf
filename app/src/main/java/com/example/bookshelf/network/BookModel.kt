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
    val subtitle : String?,
    val authors : Array<String>,
    val publisher : String?,
    val publishedDate : String,
    val description : String?,
    val imageLinks : ImageLinks
)

@Serializable
data class ImageLinks(
    val smallThumbnail : String,
    val thumbnail: String
)

