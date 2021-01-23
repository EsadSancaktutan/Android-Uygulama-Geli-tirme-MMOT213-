package com.example.meeshaber

import android.content.Context
import android.content.SharedPreferences
import com.example.meeshaber.MeeSLib.ApplicationContext.Companion.appContext

object MeeSLib {

    private const val NAME = "MeeSLib"
    private const val MODE = Context.MODE_PRIVATE
    lateinit var sharedPreferences: SharedPreferences

    open class ApplicationContext {
        companion object
        {
            lateinit var appContext: Context
        }
    }

    fun Context(context: Context) {
        appContext = context
        sharedPreferences = appContext.getSharedPreferences(NAME, MODE)
    }

    fun VeriKaydetString(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    fun VeriGetirString(key: String): String {
        val value = sharedPreferences.getString(key, "").toString()
        return value
    }
}