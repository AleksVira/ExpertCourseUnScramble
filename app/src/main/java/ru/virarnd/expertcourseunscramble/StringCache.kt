package ru.virarnd.expertcourseunscramble

import android.content.SharedPreferences

interface StringCache {

    fun read(): String

    fun save(value: String)

    class Base(
        private val sharedPreferences: SharedPreferences,
        private val key: String,
        private val defaultValue: String = ""
    ) : StringCache {

        override fun read(): String =
            sharedPreferences.getString(key, defaultValue) ?: defaultValue

        override fun save(value: String) {
            sharedPreferences.edit().putString(key, value).apply()
        }
    }
}