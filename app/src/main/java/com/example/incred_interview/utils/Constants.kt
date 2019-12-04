package com.example.incred_interview.utils

class Constants {
    companion object{
        const val PHOTOS_ID_URL = "?method=flickr.people.getPublicPhotos&nojsoncallback=1&format=json&user_id=135487628%40N06"
        const val PHOTOS_SOURCE_URL = "?method=flickr.photos.getSizes&nojsoncallback=1&format=json&user_id=135487628%40N06"
        const val API_KEY_VALUE = "227be805b3d6e934926d049533bb938a"
        const val USER_ID_VALUE = "135487628%40N06"
        const val USER_ID_KEY = "user_id"
        const val API_KEY_KEY = "api_key"
        const val PHOTO_ID_KEY = "photo_id"
        const val PHOTO_THUMBNAIL = "Thumbnail"
    }
}