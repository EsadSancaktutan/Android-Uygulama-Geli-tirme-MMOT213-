package com.example.haberuygulama

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detay.*

class DetayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detay)


        var bundle = intent.extras


        textTi.text = bundle?.getString("title")
        textz.text = bundle?.getString("description")
        txtUrl.text = bundle?.getString("author")
        Picasso.get().load(bundle?.getString("image")).into(findViewById<ImageView>(R.id.imageView));

    }
}
