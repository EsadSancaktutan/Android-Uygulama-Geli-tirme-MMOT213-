package com.example.meeshaber.Adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.meeshaber.Model.ModelNews
import com.example.meeshaber.R
import com.squareup.picasso.Picasso


class AdapterReadLater(private val context: Context, private val readLaterList: List<ModelNews>) : BaseAdapter() {

    class ModelViewHolder {
        var newsTitle: TextView? = null
        var newsDesc: TextView? = null
        var newsImage: ImageView? = null

    }

    override fun getCount(): Int {
        return readLaterList.size
    }

    override fun getItem(position: Int): Any {
        return readLaterList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        val holder: ModelViewHolder
        val newsImageUrl = readLaterList[position].urlToImage
        val url = readLaterList[position].url
        val inflater = (context as Activity).layoutInflater

        view = inflater.inflate(R.layout.row_news, parent, false)
        holder = ModelViewHolder()

        holder.newsDesc = view?.findViewById(R.id.tvNewsDesc)
        holder.newsImage = view.findViewById(R.id.ivNewsImage)
        holder.newsTitle = view.findViewById(R.id.tvNewsTitle)

        holder.newsDesc?.text = readLaterList[position].description
        holder.newsTitle?.text = readLaterList[position].title

        try {
            Picasso.get().load(newsImageUrl).fit().into(holder.newsImage)
        } catch (e: Exception) {}

        holder.newsImage?.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            context.startActivity(browserIntent)
        }

        return view
    }
}