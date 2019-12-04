package com.example.incred_interview.ui.photos

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.incred_interview.R

class PhotosGridAdapter constructor(
    private val context: Context,
    private var photoList: List<String>
) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        val viewHolder: PhotoViewHolder
        if (convertView == null) {
            val inflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.grid_item_layout, null)
            viewHolder = PhotoViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as PhotoViewHolder
        }
        val photoUrl = photoList[position]
        loadImage(photoUrl, viewHolder.photoImageView)
        return view
    }

    override fun getItem(position: Int): String {
        return photoList[position]
    }

    override fun getCount(): Int {
        return if (photoList.isNotEmpty()) photoList.size else 0
    }

    inner class PhotoViewHolder(view: View) {
        var photoImageView: ImageView = view.findViewById(R.id.image_item)
        var photoName: TextView = view.findViewById(R.id.photo_name)

    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    fun updatePhotoList(photoList1: List<String>) {
        photoList = photoList1
        notifyDataSetChanged()
    }

    private fun loadImage(imageUrl: String, view: ImageView) {
        Glide.with(context)
            .load(imageUrl)
            .transition(DrawableTransitionOptions.withCrossFade())
            .fitCenter()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(view)
    }
}