package ru.virarnd.expertcourseunscramble.game

import android.view.View
import androidx.annotation.StringRes
import androidx.appcompat.widget.AppCompatButton
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isNotEnabled
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not

class


ButtonUi(
    id: Int,
    @StringRes textResId: Int,
//    isVisible: Boolean,
//    isActive: Boolean,
    containerIdMatcher: Matcher<View>,
    containerClassTypeMatcher: Matcher<View>
) : AbstractButton(
    onView(
        allOf(
            containerIdMatcher,
            containerClassTypeMatcher,
            withId(id),
            withText(textResId),
            isAssignableFrom(AppCompatButton::class.java)
        )
    )
) {

    fun assertIsVisible() {
        interaction.check(matches(isCompletelyDisplayed()))
    }

    fun assertNotVisible() {
        interaction.check(matches(not(isDisplayed())))
    }

    fun assertIsDisabled() {
        interaction.check(matches(isNotEnabled()))
            .check(matches(isCompletelyDisplayed()))
    }

}


abstract class AbstractButton(protected val interaction: ViewInteraction) {
    fun click() {
        interaction.perform(ViewActions.click())
    }
}
