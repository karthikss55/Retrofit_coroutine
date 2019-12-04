package com.example.incred_interview.data

import com.google.gson.annotations.SerializedName


data class PhotoResponse(@SerializedName("photos") val page: Photos)
data class Photos(
    @SerializedName("page") val page: Int,
    @SerializedName("pages") val pages: Int,
    @SerializedName("perpage") val perpage: Int,
    @SerializedName("total") val total: String,
    @SerializedName("photo") val photoList: List<PhotoMeta>

)

data class PhotoMeta(@SerializedName("id") val id: String)

data class PhotoSizesModel(@SerializedName("size") val sizeList: List<PhotoSize>)

data class PhotoSize(
    @SerializedName("label") val label: String,
    @SerializedName("source") val photoUrl: String
)

data class PhotoSourceResponse(@SerializedName("sizes") val sizes:PhotoSizesModel )

