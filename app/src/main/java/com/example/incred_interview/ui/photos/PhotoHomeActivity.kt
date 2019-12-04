package com.example.incred_interview.ui.photos

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.example.incred_interview.R
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.photos_main_lyt.*
import java.time.Duration

class PhotoHomeActivity : AppCompatActivity() {

    private lateinit var photoViewModel: PhotoViewModel
    private lateinit var photoGridAdapter: PhotosGridAdapter
    private var mCompositeDisposable = CompositeDisposable()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.photos_main_lyt)
        photoViewModel = getViewModel()
        observerResponse()
        photoViewModel.getPhotoList()
        setupAdapter()
        Toast.makeText(this,"HELLO",Toast.LENGTH_SHORT).show()
    }

    private fun setupAdapter() {
        photoGridAdapter = PhotosGridAdapter(this, arrayListOf<String>())
        photos_grid.adapter = photoGridAdapter
    }

    private fun observerResponse() {
        photoViewModel.photoUrlList.observe(this, Observer {
            it?.let {
                photoGridAdapter.updatePhotoList(it)
            }
        })

        photoViewModel.showProgress.observe(this, Observer {
            it?.let {
                showProgressBar(it)
            }
        })
    }

    private fun getViewModel(): PhotoViewModel {
        return ViewModelProviders.of(this).get(PhotoViewModel::class.java)

    }

    override fun onDestroy() {
        super.onDestroy()
        mCompositeDisposable.clear()
    }

    private fun showProgressBar(show: Boolean) {
        if (show)
            grid_progress.visibility = View.VISIBLE
        else
            grid_progress.visibility = View.GONE

    }


}