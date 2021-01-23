package com.example.meeshaber.Fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import androidx.fragment.app.Fragment
import com.example.meeshaber.Adapter.AdapterReadLater
import com.example.meeshaber.MeeSLib
import com.example.meeshaber.Model.ModelNews
import com.example.meeshaber.R

class ReadLaterFragment : Fragment() {
    val readLaterList: ArrayList<ModelNews> = ArrayList()
    lateinit var gridReadLater: GridView
    lateinit var readLaterAdapter:AdapterReadLater

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_readlater, container, false)

        gridReadLater = view.findViewById(R.id.gridReadLater)

        val database = context!!.openOrCreateDatabase("Haber", Context.MODE_PRIVATE, null)
        val cursor = database.rawQuery("SELECT * FROM TBLSONRAOKUNACAKLAR WHERE EPOSTA='${MeeSLib.VeriGetirString("EMAIL")}'", null)

        if (cursor.moveToFirst()) {
            do {
                val modelNews = ModelNews()
                modelNews.title = cursor.getString(0)
                modelNews.description = cursor.getString(1)
                modelNews.url = cursor.getString(2)
                modelNews.urlToImage = cursor.getString(3)
                readLaterList.add(modelNews)
                readLaterAdapter = AdapterReadLater(context!!, readLaterList)
                readLaterAdapter.notifyDataSetChanged()
                gridReadLater.adapter = readLaterAdapter
            } while (cursor.moveToNext())
        }
        cursor.close()
        database.close()
        return view
    }
}