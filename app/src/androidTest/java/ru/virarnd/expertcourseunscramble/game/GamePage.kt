package ru.virarnd.expertcourseunscramble.game

import android.view.View
import android.widget.LinearLayout
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import org.hamcrest.Matcher

class GamePage(scrambledWord: String, correctWord: String) {

    private val containerIdMatcher: Matcher<View> = withParent(withId(R.id.rootlayout))
    private val containerClassTypeMatcher: Matcher<View> = withParent(isAssignableFrom(LinearLayout::class.java))


    private val scrambledWordUi = ScrambledWordUi(
        text = scrambledWord, containerIdMatcher = containerIdMatcher, containerClassTypeMatcher = containerClassTypeMatcher
    )

    private val inputUi = InputFieldUi(
        containerIdMatcher = containerIdMatcher, containerClassTypeMatcher = containerClassTypeMatcher
    )

    private val congratulationsUi = CongratulationsUi(
        containerIdMatcher = containerIdMatcher, containerClassTypeMatcher = containerClassTypeMatcher
    )

    private val checkUi = ButtonUi(
        id = R.id.checkButton,
        textResId = R.string.check,
        isVisible = true,
        isActive = true,
        containerIdMatcher = containerIdMatcher,
        containerClassTypeMatcher = containerClassTypeMatcher
    )
    private val skipUi = ButtonUi(
        id = R.id.skipButton,
        textResId = R.string.skip,
        isVisible = true,
        isActive = true,
        containerIdMatcher = containerIdMatcher,
        containerClassTypeMatcher = containerClassTypeMatcher
    )
    private val nextUi = ButtonUi(
        id = R.id.nextButton,
        textResId = R.string.next,
        isVisible = true,
        isActive = true,
        containerIdMatcher = containerIdMatcher,
        containerClassTypeMatcher = containerClassTypeMatcher
    )

    /*
1. TextWord
2. Input: enabled with Hint
	2a. Icon: invisible
	2b. PromptText: moreCharatersNumber
3. ButtonCheck: invisible
4. ButtonSkip: visible
5. ButtonNext: invisible
6. CongratulationsMessage: invisible
*/

    fun assertInitialState(s: String) {
        scrambledWordUi.assertTextVisible()
        inputUi.assertInputInitialState()
        congratulationsUi.assertNotVisible()
        checkUi.assertNotVisible()
        skipUi.assertIsVisible()
        nextUi.assertNotVisible()
    }

    fun typeLetter(letter: String) {
        scrambledWordUi.typeLetter(letter)
    }

    fun assertWordUncompleted() {
        scrambledWordUi.assertTextVisible()
        inputUi.assertNotEnoughtChars()
        congratulationsUi.assertNotVisible()
        checkUi.assertNotVisible()
        skipUi.assertIsVisible()
        nextUi.assertNotVisible()
    }

    fun assertWordCompleted() {
        scrambledWordUi.assertTextVisible()
        inputUi.assertWordLengthTheSameAsOriginalWord()
        congratulationsUi.assertNotVisible()
        checkUi.assertIsVisible()
        skipUi.assertIsVisible()
        nextUi.assertNotVisible()
    }

    fun clickCheck() {
        checkUi.click()
    }

    fun assertCorrectWord() {
        scrambledWordUi.assertTextVisible()
        inputUi.assertWordLengthTheSameAsOriginalWord()
        congratulationsUi.assertIsVisible()
        checkUi.assertNotVisible()
        skipUi.assertNotVisible()
        nextUi.assertIsVisible()
    }

    fun clickNext() {
        nextUi.click()
    }

    fun assertErrorWord() {
        scrambledWordUi.assertTextVisible()
        inputUi.assertWordNotTheSameAsOriginalWord()
        congratulationsUi.assertNotVisible()
        checkUi.assertIsDisabled()
        skipUi.assertIsVisible()
        nextUi.assertNotVisible()
    }

    fun deleteOneLetter() {
        inputUi.deleteOneLetter()
    }

    fun clickSkip() {
        skipUi.click()
    }


}
