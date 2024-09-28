package ru.virarnd.expertcourseunscramble

import android.view.View
import androidx.appcompat.widget.AppCompatButton

interface CheckUiState {

    fun update(checkButton: AppCompatButton)

    abstract class Abstract(
        private val visible: Int,
        private val enabled: Boolean
    ) : CheckUiState {
        override fun update(checkButton: AppCompatButton) = with(checkButton) {
            visibility = visible
            isEnabled = enabled
        }
    }

    object Disabled : Abstract(visible = View.VISIBLE, enabled = false)
    object Enabled : Abstract(visible = View.VISIBLE, enabled = true)
    object Invisible : Abstract(visible = View.GONE, enabled = false)
}