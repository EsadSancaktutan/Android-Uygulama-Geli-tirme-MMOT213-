package com.example.kameragoruntuleri


import android.app.Activity
import  android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    var imageUri: Uri? = null
    val IMAGE_CAPTURE_CODE = 123
    val PERMISSION_REQUEST_CODE = 234

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val KameraBtn = findViewById(R.id.KameraBtn) as Button

        KameraBtn.setOnClickListener{

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {


                if (checkSelfPermission(android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED || checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                    Toast.makeText(this, "Kameraya Erişim Sağlanamadı!", Toast.LENGTH_SHORT).show()

                    val permission = arrayOf(
                            android.Manifest.permission.CAMERA,
                            android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                    )

                    requestPermissions(permission, PERMISSION_REQUEST_CODE)





                }else{
                    kameraAc()
                }
        } else {

            kameraAc()
        }


    }


    }

    private fun kameraAc() {
        val contentValues = ContentValues()
        contentValues.put(MediaStore.Images.Media.TITLE, "görüntü başlığı")

        imageUri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
        startActivityForResult(intent, IMAGE_CAPTURE_CODE)

    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {

        when (requestCode) {
            PERMISSION_REQUEST_CODE -> {

                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    kameraAc()
                } else {
                    Toast.makeText(this, "Kullanıcı yetki vermedi", Toast.LENGTH_SHORT).show()
                }

            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {

            val imageView = findViewById(R.id.imageView) as ImageView
            imageView.setImageURI(imageUri)
        }

    }

}