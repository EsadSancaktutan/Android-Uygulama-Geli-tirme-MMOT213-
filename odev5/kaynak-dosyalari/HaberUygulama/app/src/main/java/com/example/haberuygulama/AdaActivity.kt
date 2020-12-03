package com.example.haberuygulama

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_ada.view.*

class AdaActivity : BaseAdapter {
    var adaList = ArrayList<Main2Activity>()
    var context: Context? = null


    constructor(context: Context, newsList: ArrayList<Main2Activity>) : super() {
        this.context = context
        this.adaList = newsList
    }
    override fun getCount(): Int {
        return adaList.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val news = this.adaList[position]
        var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var Main2ActivityView = inflator.inflate(R.layout.activity_ada, null)



        Picasso.get().load(news.urlToImage).into(Main2ActivityView.imgAda);
        Main2ActivityView.adaaName.text = news.title!!

        Main2ActivityView.setOnClickListener{
            var intent = Intent(context, DetayActivity::class.java)
            intent.putExtra("title",news.title!!)
            intent.putExtra("description",news.description!!)
            intent.putExtra("image", news.urlToImage)
            intent.putExtra("author",news.author)
            context!!.startActivity(intent)

        }

        return Main2ActivityView

    }

    override fun getItem(position: Int): Any {
        return adaList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }


    }

