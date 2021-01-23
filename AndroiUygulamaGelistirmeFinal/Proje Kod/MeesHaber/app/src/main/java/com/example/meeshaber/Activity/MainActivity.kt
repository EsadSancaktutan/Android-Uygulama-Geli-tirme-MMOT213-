package com.example.meeshaber.Activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.meeshaber.MeeSLib
import com.example.meeshaber.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        MeeSLib.Context(this.applicationContext)

        val database = this.openOrCreateDatabase("Haber", Context.MODE_PRIVATE,null)
        database.execSQL("CREATE TABLE IF NOT EXISTS TBLKULLANICILAR (EPOSTA VARCHAR,SIFRE VARCHAR)")
        database.execSQL("CREATE TABLE IF NOT EXISTS TBLKAYDEDILENLER (TITLE VARCHAR,DESCRIPTION VARCHAR,URL VARCHAR,URLTOIMAGE VARCHAR,EPOSTA VARCHAR,UNIQUE(URL))")
        database.execSQL("CREATE TABLE IF NOT EXISTS TBLSONRAOKUNACAKLAR (TITLE VARCHAR,DESCRIPTION VARCHAR,URL VARCHAR,URLTOIMAGE VARCHAR,EPOSTA VARCHAR,UNIQUE(URL))")

        binding.btnRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        binding.btnLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}