package com.example.incred_interview.repository

import android.util.Log
import com.example.incred_interview.data.PhotoResponse
import com.example.incred_interview.localDataBase.AppDatabase
import com.example.incred_interview.ui.MainApplication
import com.example.incred_interview.utils.Constants
import com.example.incred_interview.utils.NetworkUtils
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class PhotoRepository {
    private var job = SupervisorJob()
    private var scope = CoroutineScope(Dispatchers.IO + job)
    private var observableList = ArrayList<Observable<String>>()
    var isFromCache: Boolean = false

    fun getPhotoResponse(){
        if (NetworkUtils.isNetworkConnected(MainApplication.getContext())) {
            getPhotoDataFromNetwork()
        } else {
            fetchDataFromLocalDatabase()
        }
    }


//    private fun getPhotoSourceUrl(photoId: String): Observable<String> {
//        return MainApplication.getRestService()?.getPhotoThumbnail(
//            Constants.API_KEY_VALUE,
//            photoId = photoId
//        )!!
//            .map {
//                it.sizes.sizeList
//            }
//            .flatMapIterable {
//                it
//            }
//            .filter {
//                it.label.equals(Constants.PHOTO_THUMBNAIL)
//            }.map {
//                it.photoUrl
//            }
//
//    }

    private fun getDatabase(): AppDatabase {
        return MainApplication.buildDatabase()
    }

//    private fun fetchDataFromNetwork(): Observable<String> {
//        isFromCache = false
//        Log.d("KARTHIK","cache set")
//        return MainApplication.getRestService()?.getPhotoIds(Constants.API_KEY_VALUE)!!
//            .map { response: PhotoResponse ->
//                response.page.photoList
//            }
//            .flatMapIterable {
//                it
//            }.flatMap {
//                getPhotoSourceUrl(it.id)
//            }
//    }

    private fun getPhotoDataFromNetwork(){
        scope.launch {
           val photoIds =  MainApplication.getRestService()?.getPhotoIds(Constants.API_KEY_VALUE)
            withContext(Dispatchers.Main){
                val data = photoIds?.body()
            }
        }
    }

    private fun fetchDataFromLocalDatabase(): Observable<String> {
        isFromCache = true
        return getDatabase().PhotoDao().getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .flatMapIterable { it }
            .map {
                it.imageUrl
            }
    }
}