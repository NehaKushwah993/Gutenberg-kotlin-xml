package com.nehak.gutenberg_task.util

import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.nehak.gutenberg_task.BuildConfig
import com.nehak.gutenberg_task.R
import com.nehak.gutenberg_task.models.Author


/**
 * Created by Neha Kushwah on 3/29/21.
 */
class CustomBindings {

    companion object {

        const val RADIUS = 10;

        @JvmStatic
        @BindingAdapter("imageUrl")
        fun setImageUrl(imgView: ImageView, imgUrl: String?) {

            imgUrl?.let {
                val imgUri = it.toUri().buildUpon().scheme("https").build()
                val requestOptions = RequestOptions().transform(MultiTransformation(CenterCrop(), RoundedCorners(RADIUS))).placeholder(R.color.grey)
                        .error(R.color.grey)
                Glide.with(imgView.context)
                        .load(imgUri)
                        .apply(requestOptions)
                        .into(imgView)
            }
        }

        @JvmStatic
        @BindingAdapter("imageUrlWithId")
        fun setImageUrlWithId(imgView: ImageView, id: Int?) {
            // Example : https://www.gutenberg.org/cache/epub/829/pg829.cover.medium.jpg
            val formedUrl = BuildConfig.IMAGE_BASE_URL + id + "/pg" + id + ".cover.medium.jpg";
            setImageUrl(imgView, formedUrl)
        }

        @JvmStatic
        @BindingAdapter("authors")
        fun setAuthors(textView: TextView, authors: List<Author>?) {
            if (authors != null && authors.size > 0) {
                textView.setText(authors[0].name)
            } else {
                textView.setText("")
            }

        }
    }
}