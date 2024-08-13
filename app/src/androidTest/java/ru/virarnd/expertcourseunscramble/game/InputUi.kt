package ru.virarnd.expertcourseunscramble.game

import android.view.KeyEvent
import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.pressKey
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import ru.virarnd.expertcourseunscramble.R
import ru.virarnd.expertcourseunscramble.TextInputLayoutErrorEnabledMatcher
import ru.virarnd.expertcourseunscramble.TextInputLayoutHasErrorText
import ru.virarnd.expertcourseunscramble.TextInputLayoutHintEnabledMatcher

class InputUi(containerIdMatcher: Matcher<View>, containerClassTypeMatcher: Matcher<View>) {

    private val inputLayoutId: Int = R.id.inputLayout

    private val layoutInteraction: ViewInteraction = onView(
        allOf(
            containerIdMatcher,
            containerClassTypeMatcher,
            withId(inputLayoutId),
            isAssignableFrom(TextInputLayout::class.java)
        )
    )

    private val inputInteraction: ViewInteraction = onView(
        allOf(
            containerIdMatcher,
            containerClassTypeMatcher,
            withId(R.id.inputEditText),
            withParent(withId(inputLayoutId)),
            isAssignableFrom(TextInputEditText::class.java),
        )
    )


    fun assertInputInitialState() {
        layoutInteraction.check(matches(isEnabled()))
            .check(matches(TextInputLayoutErrorEnabledMatcher(false)))
            .check(matches(TextInputLayoutHintEnabledMatcher(withText(R.string.hint_text).toString())))
        inputInteraction.check(matches(withText("")))
//            .check(matches(withHint(R.string.hintText)))
    }

    fun typeLetter(letter: String) {
        inputInteraction.perform(typeText(letter), closeSoftKeyboard())
    }

    fun assertNotEnoughChars() {
        layoutInteraction.check(matches(isEnabled()))
            .check(matches(TextInputLayoutErrorEnabledMatcher(false)))
            .check(matches(TextInputLayoutHintEnabledMatcher("")))
    }

    fun assertWordLengthTheSameAsOriginalWord() {
        layoutInteraction.check(matches(isEnabled()))
            .check(matches(TextInputLayoutErrorEnabledMatcher(false)))
            .check(matches(TextInputLayoutHintEnabledMatcher("")))
    }

    fun assertWordNotTheSameAsOriginalWord() {
        layoutInteraction.check(matches(isEnabled()))
            .check(matches(TextInputLayoutErrorEnabledMatcher(true)))
            .check(matches(TextInputLayoutHasErrorText(R.string.error_incorrect_word)))
            .check(matches(TextInputLayoutHintEnabledMatcher("")))
    }

    fun deleteOneLetter() {
        inputInteraction.perform(click(), pressKey(KeyEvent.KEYCODE_MOVE_END))
            .perform(pressKey(KeyEvent.KEYCODE_DEL))
    }

}
