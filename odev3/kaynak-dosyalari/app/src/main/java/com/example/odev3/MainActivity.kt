package com.example.odev3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var firstname = findViewById<TextView>(R.id.firstname)
        val lastname = findViewById<TextView>(R.id.lastname)
        val age = findViewById<TextView>(R.id.age)
        val email = findViewById<TextView>(R.id.email)
        val pass = findViewById<TextView>(R.id.pass)
        val passAgain = findViewById<TextView>(R.id.passAgain)



        findViewById<Button>(R.id.button).setOnClickListener{
            val ageText = age.text.toString()
            if (!alphanumericControl(firstname.text.toString()))
                Toast.makeText(applicationContext, "Ad bilgisini yanlış girdiniz", Toast.LENGTH_SHORT).show()
            else if (!alphanumericControl(lastname.text.toString()))
                Toast.makeText(applicationContext, "Soyad bilgisini yanlış girdiniz", Toast.LENGTH_SHORT).show()
            else if (ageText.isEmpty() || (ageText.toInt() < 18 || ageText.toInt() > 100))
                Toast.makeText(applicationContext, "Yaş bilgisini yanlış girdiniz", Toast.LENGTH_SHORT).show()
            else if (!emailControl(email.text.toString()))
                Toast.makeText(applicationContext, "E-posta formatı hatalıdır", Toast.LENGTH_SHORT).show()
            else if(pass.text.toString().length < 5)
                Toast.makeText(applicationContext, "Şifre en az 5 karakterden oluşmalıdır.", Toast.LENGTH_SHORT).show()
            else if(pass.text.toString() != passAgain.text.toString())
                Toast.makeText(applicationContext, "Girilen şifreler uyuşmuyor.", Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(applicationContext, "Kayıt başarılı.", Toast.LENGTH_SHORT).show()

        }

    }

    private fun alphanumericControl(str: String): Boolean {
        var count = 0
        for(c in str) {
            if (c.isLetterOrDigit()) count++
        }
        return count >= 2
    }

    private fun emailControl(email: String): Boolean {
        var charStatus = false
        var charCount = 0
        for(c in email) {
            if (c == '@') {
                if (charCount < 2) return false
                charStatus = true
                charCount = 0
            }
            charCount++
        }
        return charStatus && charCount > 2
    }
}
