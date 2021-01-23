package com.example.meeshaber.Fragment

import android.app.ProgressDialog
import android.os.AsyncTask
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.meeshaber.Adapter.AdapterNews
import com.example.meeshaber.Model.ModelNews
import com.example.meeshaber.R
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class NewsFragment : Fragment() {

    val newsList: ArrayList<ModelNews> = ArrayList()
    lateinit var newsAdapter:AdapterNews
    lateinit var recycNews: RecyclerView
    lateinit var progressDialog: ProgressDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_news, container, false)
        val url = "http://newsapi.org/v2/top-headlines?country=tr&apiKey=e01d5275ddf54278810629835cf1613c"

        Thread({
            activity?.runOnUiThread {
                progressDialog = ProgressDialog(context)
                progressDialog.setCancelable(false)
                progressDialog.setTitle("Haberler Yükleniyor...")
                progressDialog.show()
            }
            Thread.sleep(3000)
            AsyncTaskHandler().execute(url)
            activity?.runOnUiThread {
                progressDialog.dismiss()
            }
        }).start()



        recycNews = view.findViewById(R.id.recycNews)
        recycNews.setHasFixedSize(true)
        recycNews.layoutManager = LinearLayoutManager(activity)
        return view
    }

    inner class AsyncTaskHandler: AsyncTask<String, String, String>() {

        override fun doInBackground(vararg url: String?): String {
            val res:String
            val connection = URL(url[0]).openConnection() as HttpURLConnection
            connection.readTimeout = 10000
            connection.setRequestProperty(
                "User-Agent",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11"
            )
            connection.connectTimeout = 10000
            connection.requestMethod = "GET"
            connection.useCaches = false
            connection.allowUserInteraction = false
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded")
            try {
                connection.connect()

                val bufferedReader = BufferedReader(InputStreamReader(connection.inputStream))
                res = bufferedReader.readLine()
            }
            finally {
                connection.disconnect()
            }
            return res
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            JsonParse(result!!)
        }

        fun JsonParse(jsonString:String)
        {
            newsList.clear()
            val jsonObject = JSONObject(jsonString.substring(jsonString.indexOf("{"), jsonString.lastIndexOf("}") + 1))
            val jsonArray = jsonObject.getJSONArray("articles")
            for (i in 0 until jsonArray.length()) {

                val modelNews = ModelNews()
                modelNews.title = jsonArray.getJSONObject(i).getString("title")
                modelNews.description = jsonArray.getJSONObject(i).getString("description")
                modelNews.url = jsonArray.getJSONObject(i).getString("url")
                modelNews.urlToImage = jsonArray.getJSONObject(i).getString("urlToImage")

                newsList.add(modelNews)
                newsAdapter = AdapterNews(context!!, newsList)
                newsAdapter.notifyDataSetChanged()
                recycNews.adapter = newsAdapter
            }
        }

    }
}