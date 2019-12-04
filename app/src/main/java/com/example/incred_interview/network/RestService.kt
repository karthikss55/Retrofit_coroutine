package com.example.incred_interview.network

import com.example.incred_interview.data.PhotoResponse
import com.example.incred_interview.data.PhotoSourceResponse
import com.example.incred_interview.utils.Constants.Companion.API_KEY_KEY
import com.example.incred_interview.utils.Constants.Companion.PHOTOS_ID_URL
import com.example.incred_interview.utils.Constants.Companion.PHOTOS_SOURCE_URL
import com.example.incred_interview.utils.Constants.Companion.PHOTO_ID_KEY
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RestService {
    @GET(PHOTOS_ID_URL)
    suspend fun getPhotoIds(@Query(API_KEY_KEY) apiKey:String
                    ): Response<PhotoResponse>
    @GET(PHOTOS_SOURCE_URL)
    suspend fun getPhotoThumbnail(@Query(API_KEY_KEY) apiKey:String,
                          @Query(PHOTO_ID_KEY) photoId: String
    ): Response<PhotoSourceResponse>
}