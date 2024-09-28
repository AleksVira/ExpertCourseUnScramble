package ru.virarnd.expertcourseunscramble

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.virarnd.expertcourseunscramble.game.GamePage

@RunWith(AndroidJUnit4::class)
class ScenarioTest {

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    private lateinit var gamePage: GamePage

    @Before
    fun setup() {
        gamePage = GamePage(scrambledWord = "erutaN", correctWord = "Nature")
    }

    /**
     * UG TestCase #1 "Happy Path"
     */

    @Test
    fun caseNumber1() {

        gamePage.assertInitialState()

        gamePage.typeLetter("N")
        gamePage.assertWordUncompleted()

        gamePage.typeLetter("a")
        gamePage.assertWordUncompleted()

        gamePage.typeLetter("tur")
        gamePage.assertWordUncompleted()

        gamePage.typeLetter("e")
        gamePage.assertWordCompleted()

        gamePage.clickCheck()
        gamePage.assertCorrectWord()

        gamePage.clickNext()
        gamePage = GamePage(scrambledWord = "tnemtrapA", correctWord = "Apartment")
        gamePage.assertInitialState()

    }


    /**
     * UG TestCase #2 "Wrong Path"
     */
    @Test
    fun caseNumber2() {

        gamePage.assertInitialState()

        gamePage.typeLetter("N")
        gamePage.assertWordUncompleted()

        gamePage.typeLetter("atura")
        gamePage.assertWordCompleted()

        gamePage.clickCheck()
        gamePage.assertErrorWord()

        gamePage.deleteOneLetter()
        gamePage.assertWordUncompleted()

        gamePage.typeLetter("Q")
        gamePage.assertWordCompleted()

        gamePage.clickCheck()
        gamePage.assertErrorWord()

        gamePage.clickSkip()
        gamePage = GamePage(scrambledWord = "tnemtrapA", correctWord = "Apartment")
        gamePage.assertInitialState()
    }


    /**
     * UG TestCase #3 "Other path"
     */
    @Test
    fun caseNumber3() {

        gamePage.assertInitialState()

        gamePage.clickSkip()
        gamePage = GamePage(scrambledWord = "tnemtrapA", correctWord = "Apartment")
        gamePage.assertInitialState()

        gamePage.typeLetter("A")
        gamePage.assertWordUncompleted()

        gamePage.clickSkip()
        gamePage = GamePage(scrambledWord = "gnitirW", correctWord = "Writing")
        gamePage.assertInitialState()

        gamePage.typeLetter("W")
        gamePage.assertWordUncompleted()

        gamePage.typeLetter("riting")
        gamePage.assertWordCompleted()

        gamePage.deleteOneLetter()
        gamePage.assertWordUncompleted()

        gamePage.deleteOneLetter()
        gamePage.assertWordUncompleted()

        gamePage.typeLetter("ng")
        gamePage.assertWordCompleted()

        gamePage.clickSkip()
        gamePage = GamePage(scrambledWord = "krowemoH", correctWord = "Homework")
        gamePage.assertInitialState()
    }

}

/*
Scrambledword
Nextword
Secondword
Thirdword
Anotherword
* */