package com.example.haberuygulama

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import org.json.JSONObject
import java.io.IOException


class MainActivity : AppCompatActivity() {

    private val client = OkHttpClient()
    var adapter : AdaActivity? = null
    private lateinit var adaList: List<Main2Activity>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        newsApiCallWithUrl("http://newsapi.org/v2/top-headlines?country=tr&apiKey=374828a072ee4cdba85aceecdefaa5c8")



        Handler().postDelayed({
            adapter = AdaActivity(this, adaList as ArrayList<Main2Activity>)
            ada1.adapter = adapter
        }, 2000)

    }

    fun newsApiCallWithUrl(url: String){
        val req = Request.Builder().url(url).build()

        client.newCall(req).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                println("Hata  : ${e.message}")
            }

            override fun onResponse(call: Call, response: Response) {
                val responseStr = response.body?.string()
                val data = JSONObject(responseStr)
                adaList = Gson().fromJson(data.getString("articles"), Array<Main2Activity>::class.java).toList()
            }
        })
    }
}
