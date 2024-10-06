package ru.virarnd.expertcourseunscramble

import android.app.Application

class UnscrambleApp : Application() {

    lateinit var viewModel: GameViewModel

    override fun onCreate() {
        super.onCreate()
        viewModel = GameViewModel(GameRepository.Base(ShuffleStrategy.Reverse()))
    }
}