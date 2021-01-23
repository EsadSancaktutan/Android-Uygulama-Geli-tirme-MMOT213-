package com.example.meeshaber.Activity

import android.app.*
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.example.meeshaber.Fragment.NewsFragment
import com.example.meeshaber.Fragment.ReadLaterFragment
import com.example.meeshaber.Fragment.SavesFragment
import com.example.meeshaber.MeeSLib
import com.example.meeshaber.R
import com.example.meeshaber.databinding.ActivityDashboardBinding


class DashboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardBinding
    private val channelId = "newsNotifications"
    private val description = "Haber Bildirimleri"
    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var notificationBuilder: Notification.Builder


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val newsFragment = NewsFragment()
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.content, newsFragment, "")
        fragmentTransaction.commit()

        binding.bottomNavigation.selectedItemId = R.id.nav_news
        binding.bottomNavigation.setOnNavigationItemSelectedListener(selectedItemListener)
    }

    private val selectedItemListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.nav_news -> {
                val newsFragment = NewsFragment()
                val fragmentTransaction = supportFragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.content, newsFragment, "")
                fragmentTransaction.commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_saves -> {
                val savesFragment = SavesFragment()
                val fragmentTransaction = supportFragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.content, savesFragment, "")
                fragmentTransaction.commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_readlater -> {
                val readLaterFragment = ReadLaterFragment()
                val fragmentTransaction = supportFragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.content, readLaterFragment, "")
                fragmentTransaction.commit()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_bar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id = item.itemId
        if (id == R.id.nav_changepassword)
        {
            val email = MeeSLib.VeriGetirString("EMAIL")
            changePassword(email)
        }
        else if (id == R.id.nav_deleteaccount)
        {
            val database = this.openOrCreateDatabase("Haber", Context.MODE_PRIVATE,null)
            database.execSQL("DELETE FROM TBLKULLANICILAR WHERE EPOSTA='${MeeSLib.VeriGetirString("EMAIL")}'")
            database.execSQL("DELETE FROM TBLSONRAOKUNACAKLAR WHERE EPOSTA='${MeeSLib.VeriGetirString("EMAIL")}'")
            database.execSQL("DELETE FROM TBLKAYDEDILENLER WHERE EPOSTA='${MeeSLib.VeriGetirString("EMAIL")}'")
            MeeSLib.VeriKaydetString("EMAIL","")
            startActivity(Intent(this,MainActivity::class.java))
            finish()
            Toast.makeText(this, "Hesabın Başarıyla Silindi.", Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun changePassword(email: String) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("Şifre Güncelle")

        val linearLayout = LinearLayout(this)
        linearLayout.orientation = LinearLayout.VERTICAL
        linearLayout.setPadding(10, 10, 10, 10)

        val editText = EditText(this)
        editText.hint = "Yeni Şifrenizi Girin"
        linearLayout.addView(editText)

        builder.setView(linearLayout)

        builder.setPositiveButton("Güncelle", DialogInterface.OnClickListener { dialog, which ->
            val value = editText.text.toString().trim()

            if (!TextUtils.isEmpty(value)) {

                val database = this.openOrCreateDatabase("Haber", Context.MODE_PRIVATE,null)
                database.execSQL("UPDATE TBLKULLANICILAR SET SIFRE= '$value' WHERE EPOSTA='${MeeSLib.VeriGetirString("EMAIL")}'")

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    notificationChannel = NotificationChannel(channelId, description, NotificationManager.IMPORTANCE_HIGH)
                    notificationChannel.enableLights(true)
                    notificationChannel.enableVibration(false)
                    notificationManager.createNotificationChannel(notificationChannel)

                    notificationBuilder = Notification.Builder(this, channelId)
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setContentTitle(getString(R.string.app_name))
                        .setContentText("Şifrenizi Değiştirdiniz")
                        .setLargeIcon(BitmapFactory.decodeResource(this.resources, R.drawable.ic_launcher_background))
                } else {

                    notificationBuilder = Notification.Builder(this)
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setContentTitle(getString(R.string.app_name))
                        .setContentText("Şifrenizi Değiştirdiniz")
                        .setLargeIcon(BitmapFactory.decodeResource(this.resources, R.drawable.ic_launcher_background))
                }
                notificationManager.notify(1234, notificationBuilder.build())


            } else {
                Toast.makeText(this, "Lütfen Yeni Şifre Giriniz.", Toast.LENGTH_SHORT).show()
            }
        })
        builder.setNegativeButton("İptal", DialogInterface.OnClickListener { dialog, which ->
            dialog.dismiss()
        })
        builder.create().show()
    }


    override fun onBackPressed() {
    }
}