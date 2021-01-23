package com.example.meeshaber.Fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.meeshaber.Adapter.AdapterSaves
import com.example.meeshaber.MeeSLib
import com.example.meeshaber.Model.ModelNews
import com.example.meeshaber.R

class SavesFragment : Fragment() {
    val savesList: ArrayList<ModelNews> = ArrayList()
    lateinit var recycSaves: RecyclerView
    lateinit var savesAdapter:AdapterSaves

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_saves, container, false)

        recycSaves = view.findViewById(R.id.recycSaves)
        recycSaves.setHasFixedSize(true)
        recycSaves.layoutManager = LinearLayoutManager(activity)

        val database = context!!.openOrCreateDatabase("Haber", Context.MODE_PRIVATE, null)
        val cursor = database.rawQuery("SELECT * FROM TBLKAYDEDILENLER WHERE EPOSTA='${MeeSLib.VeriGetirString("EMAIL")}'", null)

        if (cursor.moveToFirst()) {
            do {
                val modelNews = ModelNews()
                modelNews.title = cursor.getString(0)
                modelNews.description = cursor.getString(1)
                modelNews.url = cursor.getString(2)
                modelNews.urlToImage = cursor.getString(3)
                savesList.add(modelNews)
                savesAdapter = AdapterSaves(context!!, savesList)
                savesAdapter.notifyDataSetChanged()
                recycSaves.adapter = savesAdapter
            } while (cursor.moveToNext())
        }
        cursor.close()
        database.close()

        return view
    }
}