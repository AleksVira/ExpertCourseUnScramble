package ru.virarnd.expertcourseunscramble

import ru.virarnd.expertcourseunscramble.databinding.ActivityMainBinding

interface GameUiState {

    fun update(binding: ActivityMainBinding): Unit =
        throw IllegalStateException("handle it!") //todo Remove it!

    data class Initial(val wordAndScrambledWord: WordAndScrambledWord) : GameUiState

    data class WordUncompleted(val userText: String) : GameUiState

    data class WordCompleted(val userText: String) : GameUiState

    data class CorrectWord(val userText: String) : GameUiState

    data class ErrorWord(val userText: String) : GameUiState

}
