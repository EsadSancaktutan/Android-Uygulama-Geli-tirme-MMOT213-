package com.example.meeshaber.Activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.meeshaber.MeeSLib
import com.example.meeshaber.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.etEmail.error = "Geçersiz E-Posta Adresi"
                binding.etEmail.isFocusable = true
            } else if (password.length < 6) {
                binding.etPassword.error = "Şifre 6 Karakterden Kısa Olamaz"
                binding.etPassword.isFocusable = true
            } else {
                createUser(email, password)
            }
        }
    }

    private fun createUser(email: String, password: String) {
        val database = this.openOrCreateDatabase("Haber", Context.MODE_PRIVATE, null)
        val cursor = database.rawQuery("SELECT * FROM TBLKULLANICILAR WHERE EPOSTA='$email'", null)

        if (cursor.count == 0) {
            val sqlText = "INSERT INTO TBLKULLANICILAR (EPOSTA,SIFRE) VALUES (?, ?)"
            val statement = database.compileStatement(sqlText)

            statement.bindString(1, email)
            statement.bindString(2, password)
            statement.execute()

            MeeSLib.VeriKaydetString("EMAIL", email)
            startActivity(Intent(this, DashboardActivity::class.java))
            finish()
        } else {
            Toast.makeText(this, "Bu E-Posta Adresi Kullanılmaktadır", Toast.LENGTH_SHORT)
                .show()
        }
    }
}