package com.example.meeshaber.Activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.meeshaber.MeeSLib
import com.example.meeshaber.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            val database = this.openOrCreateDatabase("Haber", Context.MODE_PRIVATE, null)
            val cursor = database.rawQuery("SELECT * FROM TBLKULLANICILAR WHERE EPOSTA='$email' AND SIFRE='$password'", null)

            if (cursor.count != 0)
            {
                startActivity(Intent(this, DashboardActivity::class.java))
                finish()
                MeeSLib.VeriKaydetString("EMAIL", email)
                MeeSLib.VeriKaydetString("PASSWORD", password)

                Toast.makeText(this, "Başarıyla Giriş Yaptın.", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(this, "Girdiğiniz Bilgilere Ait Bir Hesap Bulunamadı.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}