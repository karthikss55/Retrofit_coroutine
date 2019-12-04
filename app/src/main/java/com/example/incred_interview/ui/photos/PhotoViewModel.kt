package com.example.incred_interview.ui.photos

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.incred_interview.localDataBase.PhotoEntity
import com.example.incred_interview.repository.PhotoRepository
import com.example.incred_interview.ui.MainApplication
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class PhotoViewModel : ViewModel() {

    private var mCompositeDisposable = CompositeDisposable()
    var photoUrlList = MutableLiveData<ArrayList<String>>()
    private var localUrlList = ArrayList<String>()
    private var photoRepositiory: PhotoRepository = PhotoRepository()
    var showProgress = MutableLiveData<Boolean>()

    fun getPhotoList() {
        showProgress.value = true

        photoRepositiory.getPhotoResponse()
//            .subscribeOn(Schedulers.newThread())
//            .observeOn(AndroidSchedulers.mainThread())
//            .doOnComplete {
//                showProgress.value = false
//                Log.d("KARTHIK", "onComplete")
//                if (localUrlList.isNotEmpty()) {
//                    photoUrlList.value = localUrlList
//                }
//            }
//            .subscribe(photoObserver())

    }


    private fun photoObserver(): Observer<String> {
        return object : Observer<String> {
            override fun onComplete() {
            }

            override fun onSubscribe(d: Disposable) {
                mCompositeDisposable.add(d)
                //delete old photos and update database with latest response
                deleteOldPhotoDetails()
            }

            override fun onNext(url: String) {
                Log.d("KARTHIK", "onNext:" + url)
                localUrlList.add(url)
                cacheUrlLocally(url)
                photoUrlList.value = localUrlList
            }

            override fun onError(e: Throwable) {
            }
        }
    }


    override fun onCleared() {
        super.onCleared()
        mCompositeDisposable.clear()
    }

    private fun deleteOldPhotoDetails() {
        if (!photoRepositiory.isFromCache) {
            MainApplication.buildDatabase().PhotoDao().deleteAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                }, {
                }).apply {
                    mCompositeDisposable.add(this)
                }
        }
    }

    private fun cacheUrlLocally(url: String) {
        if (!photoRepositiory.isFromCache) {
            val photoEntity = PhotoEntity(0, url)
            MainApplication.buildDatabase().PhotoDao().insertPhoto(photoEntity)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                }, {
                }).apply {
                    mCompositeDisposable.add(this)
                }
        }
    }
}