package ru.virarnd.expertcourseunscramble

import android.view.View
import androidx.test.espresso.matcher.BoundedMatcher
import com.google.android.material.textfield.TextInputLayout
import org.hamcrest.Description

class TextInputLayoutHintEnabledMatcher(private val hintText: String) :
    BoundedMatcher<View, TextInputLayout>(TextInputLayout::class.java) {

    override fun describeTo(description: Description) {
        description.appendText("hint enabled doesn't match with expected $hintText")
    }

    override fun matchesSafely(item: TextInputLayout): Boolean =
        when {
            !item.isHintEnabled && hintText.isEmpty() -> true
            item.isHintEnabled == hintText.isNotEmpty() && item.hint == hintText -> true
            else -> false
        }

}