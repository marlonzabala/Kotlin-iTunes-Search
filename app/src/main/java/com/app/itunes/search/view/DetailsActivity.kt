package com.app.itunes.search.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.app.itunes.search.R
import com.app.itunes.search.viewModel.DetailsActivityViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {
    private lateinit var viewModel : DetailsActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        viewModel = ViewModelProvider(this).get(DetailsActivityViewModel::class.java)
        viewModel.itunesItem.observe(this, Observer {
            textViewGenre.text = it.primaryGenreName
            textViewTitle.text = it.trackName
            textViewPrice.text = "$ " + it.trackPrice.toString()
            textViewDescription.text = it.longDescription

            val thumbnailRequest = Glide.with(this)
                .load(it.artworkUrl100)

            val requestOption = RequestOptions()
                .placeholder(R.mipmap.ic_launcher)
                .centerCrop()
                .error(R.mipmap.placeholder)
                .dontTransform()

            // Get higher quality image
            Glide.with(this)
                .load(it.artworkUrl100.replace("100","600"))
                .thumbnail(thumbnailRequest)
                .apply(requestOption)
                .into(imageViewDetailsArtwork)
        })

        viewModel.loadMovie()
    }

    override fun onBackPressed() {
        startActivity(Intent(this, SearchActivity::class.java)
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
        finish()
    }
}