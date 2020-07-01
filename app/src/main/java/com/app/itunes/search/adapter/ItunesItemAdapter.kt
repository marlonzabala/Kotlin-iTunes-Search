package com.app.itunes.search.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.itunes.search.R
import com.app.itunes.search.extensions.load
import com.app.itunes.search.network.model.ItunesItem
import com.app.itunes.search.repository.PreferenceRepository
import com.app.itunes.search.view.DetailsActivity
import kotlinx.android.synthetic.main.rv_itunes_item_child.view.*

class ItunesItemAdapter(private val context: Context) :
    RecyclerView.Adapter<ItunesItemAdapter.ViewHolder>() {
    private var list: List<ItunesItem> = ArrayList()

    fun setItunesItemList(list: List<ItunesItem>){
        this.list = list
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.trackName.text = list[position].trackName
        holder.artworkUrl.load(list[position].artworkUrl100.replace("100","400"))
        holder.price.text = "$ " + list[position].trackPrice.toString()
        holder.genre.text = list[position].primaryGenreName
        holder.rootView.setOnClickListener {
            if(list[position].trackId.toString().isNotEmpty()) {
                PreferenceRepository(context).setLastPosition(position)
                val intent = Intent(context, DetailsActivity::class.java)
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.rv_itunes_item_child,
                parent,
                false
            )
        )
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val trackName = v.textViewTrackName!!
        val artworkUrl = v.imageViewArtwork!!
        val price = v.textViewPrice!!
        val genre = v.textViewGenre!!
        val rootView = v.rootView!!
    }
}