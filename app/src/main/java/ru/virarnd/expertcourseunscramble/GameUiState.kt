package ru.virarnd.expertcourseunscramble

import android.view.View
import java.io.Serializable
import ru.virarnd.expertcourseunscramble.databinding.ActivityMainBinding

interface GameUiState : Serializable {

    fun update(binding: ActivityMainBinding)

    abstract class Abstract(
        private val scrambledText: String,
        private val inputUiState: InputUiState,
        private val skipVisibility: Int,
        private val nextVisibility: Int,
        private val congratulationsVisibility: Int,
        private val checkUiState: CheckUiState
    ) : GameUiState {

        override fun update(binding: ActivityMainBinding) = with(binding) {
            scrambledWordTextView.text = scrambledText
            inputUiState.update(this.inputLayout, this.inputEditText)
            skipButton.visibility = skipVisibility
            nextButton.visibility = nextVisibility
            congratulations.visibility = congratulationsVisibility
            checkUiState.update(checkButton)
        }
    }

    data class Initial(private val scrambledWord: String) : Abstract(
        scrambledText = scrambledWord,
        inputUiState = InputUiState.Initial(scrambledWord.length),
        skipVisibility = View.VISIBLE,
        nextVisibility = View.GONE,
        congratulationsVisibility = View.GONE,
        checkUiState = CheckUiState.Invisible
    )

    data class WordUncompleted(val scrambledWord: String) : Abstract(
        scrambledText = scrambledWord,
        inputUiState = InputUiState.Uncompleted(scrambledWord.length),
        skipVisibility = View.VISIBLE,
        nextVisibility = View.GONE,
        congratulationsVisibility = View.GONE,
        checkUiState = CheckUiState.Invisible
    )

    data class WordCompleted(val scrambledWord: String) : Abstract(
        scrambledText = scrambledWord,
        inputUiState = InputUiState.Completed,
        skipVisibility = View.VISIBLE,
        nextVisibility = View.GONE,
        congratulationsVisibility = View.GONE,
        checkUiState = CheckUiState.Enabled
    )

    data class CorrectWord(val scrambledWord: String) : Abstract(
        scrambledText = scrambledWord,
        inputUiState = InputUiState.Completed,
        skipVisibility = View.GONE,
        nextVisibility = View.VISIBLE,
        congratulationsVisibility = View.VISIBLE,
        checkUiState = CheckUiState.Invisible
    )

    data class ErrorWord(val scrambledWord: String) : Abstract(
        scrambledText = scrambledWord,
        inputUiState = InputUiState.Incorrect,
        skipVisibility = View.VISIBLE,
        nextVisibility = View.GONE,
        congratulationsVisibility = View.GONE,
        checkUiState = CheckUiState.Disabled
    )

}

