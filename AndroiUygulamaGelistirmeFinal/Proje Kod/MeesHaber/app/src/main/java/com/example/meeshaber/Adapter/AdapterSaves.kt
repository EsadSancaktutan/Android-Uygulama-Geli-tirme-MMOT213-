package com.example.meeshaber.Adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.meeshaber.Model.ModelNews
import com.example.meeshaber.R
import com.squareup.picasso.Picasso


class AdapterSaves(private val context: Context, private val savesList: List<ModelNews>) : RecyclerView.Adapter<AdapterSaves.ModelViewHolder>() {

    class ModelViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val newsTitle: TextView = view.findViewById(R.id.tvNewsTitle)
        val newsDesc: TextView = view.findViewById(R.id.tvNewsDesc)
        val newsImage: ImageView = view.findViewById(R.id.ivNewsImage)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModelViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_news, parent, false)
        return ModelViewHolder(view)
    }

    override fun onBindViewHolder(holder: ModelViewHolder, position: Int) {
        holder.newsTitle.text = savesList[position].title
        holder.newsDesc.text = savesList[position].description

        val url = savesList[position].url
        val newsImageUrl = savesList[position].urlToImage

        try {
            Picasso.get().load(newsImageUrl).fit().into(holder.newsImage)
        } catch (e: Exception) {}

        holder.itemView.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            context.startActivity(browserIntent)
        }
    }

    override fun getItemCount(): Int {
        return savesList.size
    }
}