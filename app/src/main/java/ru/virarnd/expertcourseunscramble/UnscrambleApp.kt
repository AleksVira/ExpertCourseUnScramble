package ru.virarnd.expertcourseunscramble

import android.app.Application
import android.content.Context

class UnscrambleApp : Application() {

    lateinit var viewModel: GameViewModel

    override fun onCreate() {
        super.onCreate()
        val sharedPref = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE)
        viewModel = GameViewModel(
            GameRepository.Base(
                IntCache.Base(sharedPref, "indexKey", 0),
                StringCache.Base(sharedPref, "userInputKey", ""),
                ShuffleStrategy.Reverse()
            )
        )
    }
}