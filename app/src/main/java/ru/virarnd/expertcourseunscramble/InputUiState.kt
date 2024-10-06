package ru.virarnd.expertcourseunscramble

import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.io.Serializable

interface InputUiState : Serializable {

    fun update(inputLayout: TextInputLayout, inputEditText: TextInputEditText)

    abstract class Abstract(
        private val clearText: Boolean,
//        private val hintIsVisible: Boolean,
        private val errorIsVisible: Boolean,
        private val enabled: Boolean,
        private val counterEnabled: Boolean,
        private val maxCounter: Int
    ) : InputUiState {
        override fun update(inputLayout: TextInputLayout, inputEditText: TextInputEditText) {
            inputLayout.apply {
                isErrorEnabled = errorIsVisible
                isEnabled = enabled
                if (errorIsVisible) {
                    error = context.getString(R.string.error_incorrect_word)
                }
                isCounterEnabled = counterEnabled
                counterMaxLength = maxCounter
            }
            if (clearText) {
                inputEditText.text?.clear()
            }
        }
    }

    data class Initial(val length: Int) : Abstract(
        clearText = true, errorIsVisible = false, enabled = true, counterEnabled = true, maxCounter = length
    )

    data class Uncompleted(val length: Int) : Abstract(
        clearText = false, errorIsVisible = false, enabled = true, counterEnabled = true, maxCounter = length
    )

    object Completed : Abstract(
        clearText = false, errorIsVisible = false, enabled = true, counterEnabled = false, maxCounter = 0
    )

    object Incorrect : Abstract(clearText = false, errorIsVisible = true, enabled = true, counterEnabled = false, maxCounter = 0)


}