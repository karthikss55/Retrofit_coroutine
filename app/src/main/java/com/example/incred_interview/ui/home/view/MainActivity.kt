package com.example.incred_interview.ui.home.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.incred_interview.R
import kotlinx.android.synthetic.main.lyt_main.*
import java.lang.IllegalArgumentException
import java.util.*
import kotlin.collections.ArrayList

@SuppressLint("ByteOrderMark")
class MainActivity : AppCompatActivity() {
    val url = "https://live.staticflickr.com/763/20699269305_3079ff1379_t.jpg"
    var dataList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lyt_main)

        Glide.with(this)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(image_center)
        dataList.findElement();

    }


    fun sortArray() {

    }

    private fun <T> List<T>.findElement(): T {
        return this[size / 2]

    }

}