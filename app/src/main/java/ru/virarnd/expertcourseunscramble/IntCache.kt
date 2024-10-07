package ru.virarnd.expertcourseunscramble

import android.content.SharedPreferences

interface IntCache {

    fun read(): Int

    fun save(value: Int)

    class Base(
        private val sharedPreferences: SharedPreferences,
        private val key: String,
        private val defaultValue: Int
    ) : IntCache {

        override fun read(): Int {
            return sharedPreferences.getInt(key, defaultValue)
        }

        override fun save(value: Int) {
            sharedPreferences.edit().putInt(key, value).apply()
        }
    }


}