package com.example.meeshaber.Adapter

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.meeshaber.MeeSLib
import com.example.meeshaber.Model.ModelNews
import com.example.meeshaber.R
import com.squareup.picasso.Picasso


class AdapterNews(private val context: Context, private val newsList: List<ModelNews>) : RecyclerView.Adapter<AdapterNews.ModelViewHolder>() {

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
        holder.newsTitle.text = newsList[position].title
        holder.newsDesc.text = newsList[position].description

        val url = newsList[position].url
        val newsImageUrl = newsList[position].urlToImage

        try {
            Picasso.get().load(newsImageUrl).fit().into(holder.newsImage)
        } catch (e: Exception) {}

        holder.itemView.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            context.startActivity(browserIntent)
        }

        holder.itemView.setOnLongClickListener {

            val builderOptions = arrayOf(
                "Daha Sonra Oku",
                "Kaydet"
            )

            val builder:AlertDialog.Builder = AlertDialog.Builder(context)
            builder.setTitle("İşlem Seçiniz")

            builder.setItems(builderOptions, DialogInterface.OnClickListener { dialog, which ->
                val database = context.openOrCreateDatabase("Haber", Context.MODE_PRIVATE, null)
                if (which == 0) {

                    try {

                    val sqlText = "INSERT INTO TBLSONRAOKUNACAKLAR (TITLE,DESCRIPTION,URL,URLTOIMAGE,EPOSTA) VALUES (?, ?, ?, ?, ?)"
                    val statement = database.compileStatement(sqlText)
                    statement.bindString(1, newsList[position].title)
                    statement.bindString(2, newsList[position].description)
                    statement.bindString(3, url)
                    statement.bindString(4, newsImageUrl)
                    statement.bindString(5, MeeSLib.VeriGetirString("EMAIL"))
                    statement.execute()
                    }
                    catch (e:Exception) { }
                }
                else if (which == 1) {
                    try {

                    val sqlText = "INSERT INTO TBLKAYDEDILENLER (TITLE,DESCRIPTION,URL,URLTOIMAGE,EPOSTA) VALUES (?, ?, ?, ?, ?)"
                    val statement = database.compileStatement(sqlText)
                    statement.bindString(1, newsList[position].title)
                    statement.bindString(2, newsList[position].description)
                    statement.bindString(3, url)
                    statement.bindString(4, newsImageUrl)
                    statement.bindString(5, MeeSLib.VeriGetirString("EMAIL"))
                    statement.execute()
                    }
                    catch (e:Exception) {}
                }
            })
            builder.create().show()
            true
        }
    }

    override fun getItemCount(): Int {
        return newsList.size
    }
}