package ru.virarnd.expertcourseunscramble

import android.content.Context
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.virarnd.expertcourseunscramble.game.GamePage


@RunWith(AndroidJUnit4::class)
class ScenarioTest {

    @get:Rule
    val activityScenarioRule = activityScenarioRule<MainActivity>()

    private lateinit var gamePage: GamePage

    @Before
    fun setup() {
        gamePage = GamePage(scrambledWord = "erutaN", correctWord = "Nature")
    }

    @After
    fun reset() {
        activityScenarioRule.scenario.onActivity { activity: MainActivity ->
            activity.getSharedPreferences(activity.getString(R.string.app_name), Context.MODE_PRIVATE).edit().clear().commit()
            activity.application.onCreate()
        }
    }

    /**
     * UG TestCase #1 "Happy Path"
     */

    @Test
    fun caseNumber1() {

        gamePage.assertInitialState()
        activityScenarioRule.scenario.recreate()
        gamePage.assertInitialState()

        gamePage.typeLetter("N")
        gamePage.assertWordUncompleted()
        activityScenarioRule.scenario.recreate()
        gamePage.assertWordUncompleted()

        gamePage.typeLetter("a")
        gamePage.assertWordUncompleted()
        activityScenarioRule.scenario.recreate()
        gamePage.assertWordUncompleted()

        gamePage.typeLetter("tur")
        gamePage.assertWordUncompleted()
        activityScenarioRule.scenario.recreate()
        gamePage.assertWordUncompleted()

        gamePage.typeLetter("e")
        gamePage.assertWordCompleted()
        activityScenarioRule.scenario.recreate()
        gamePage.assertWordCompleted()

        gamePage.clickCheck()
        gamePage.assertCorrectWord()
        activityScenarioRule.scenario.recreate()
        gamePage.assertCorrectWord()

        gamePage.clickNext()
        gamePage = GamePage(scrambledWord = "tnemtrapA", correctWord = "Apartment")
        gamePage.assertInitialState()
        activityScenarioRule.scenario.recreate()
        gamePage.assertInitialState()

    }


    /**
     * UG TestCase #2 "Wrong Path"
     */
    @Test
    fun caseNumber2() {

        gamePage = GamePage(scrambledWord = "erutaN", correctWord = "Nature")
        gamePage.assertInitialState()
        activityScenarioRule.scenario.recreate()
        gamePage.assertInitialState()

        gamePage.typeLetter("N")
        gamePage.assertWordUncompleted()
        activityScenarioRule.scenario.recreate()
        gamePage.assertWordUncompleted()

        gamePage.typeLetter("atura")
        gamePage.assertWordCompleted()
        activityScenarioRule.scenario.recreate()
        gamePage.assertWordCompleted()

        gamePage.clickCheck()
        gamePage.assertErrorWord()
        activityScenarioRule.scenario.recreate()
        gamePage.assertErrorWord()

        gamePage.deleteOneLetter()
        gamePage.assertWordUncompleted()
        activityScenarioRule.scenario.recreate()
        gamePage.assertWordUncompleted()

        gamePage.typeLetter("Q")
        gamePage.assertWordCompleted()
        activityScenarioRule.scenario.recreate()
        gamePage.assertWordCompleted()

        gamePage.clickCheck()
        gamePage.assertErrorWord()
        activityScenarioRule.scenario.recreate()
        gamePage.assertErrorWord()

        gamePage.clickSkip()
        gamePage = GamePage(scrambledWord = "tnemtrapA", correctWord = "Apartment")
        gamePage.assertInitialState()
        activityScenarioRule.scenario.recreate()
        gamePage.assertInitialState()
    }


    /**
     * UG TestCase #3 "Other path"
     */
    @Test
    fun caseNumber3() {

//        gamePage = GamePage(scrambledWord = "tnemtrapA", correctWord = "Apartment")
        gamePage.assertInitialState()
        activityScenarioRule.scenario.recreate()
        gamePage.assertInitialState()

        gamePage.clickSkip()
        gamePage = GamePage(scrambledWord = "tnemtrapA", correctWord = "Apartment")
        gamePage.assertInitialState()
        activityScenarioRule.scenario.recreate()
        gamePage.assertInitialState()

        gamePage.typeLetter("A")
        gamePage.assertWordUncompleted()
        activityScenarioRule.scenario.recreate()
        gamePage.assertWordUncompleted()

        gamePage.clickSkip()
        gamePage = GamePage(scrambledWord = "gnitirW", correctWord = "Writing")
        gamePage.assertInitialState()
        activityScenarioRule.scenario.recreate()
        gamePage.assertInitialState()

        gamePage.typeLetter("W")
        gamePage.assertWordUncompleted()
        activityScenarioRule.scenario.recreate()
        gamePage.assertWordUncompleted()

        gamePage.typeLetter("riting")
        gamePage.assertWordCompleted()
        activityScenarioRule.scenario.recreate()
        gamePage.assertWordCompleted()

        gamePage.deleteOneLetter()
        gamePage.assertWordUncompleted()
        activityScenarioRule.scenario.recreate()
        gamePage.assertWordUncompleted()

        gamePage.deleteOneLetter()
        gamePage.assertWordUncompleted()
        activityScenarioRule.scenario.recreate()
        gamePage.assertWordUncompleted()

        gamePage.typeLetter("ng")
        gamePage.assertWordCompleted()
        activityScenarioRule.scenario.recreate()
        gamePage.assertWordCompleted()

        gamePage.clickSkip()
        gamePage = GamePage(scrambledWord = "krowemoH", correctWord = "Homework")
        gamePage.assertInitialState()
        activityScenarioRule.scenario.recreate()
        gamePage.assertInitialState()
    }

}
