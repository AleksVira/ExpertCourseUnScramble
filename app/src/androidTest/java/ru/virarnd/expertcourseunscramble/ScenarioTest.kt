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
        gamePage = GamePage(scrambledWord = "cSarbmeldowdr", correctWord = "Scrambledword")
    }

    /**
     * UG TestCase #1 "Happy Path"
     */

    @Test
    fun caseNumber1() {

        gamePage.assertInitialState("cSarbmeldowdr")

        gamePage.typeLetter("S")
        gamePage.assertWordUncompleted()

        gamePage.typeLetter("c")
        gamePage.assertWordUncompleted()

        gamePage.typeLetter("rambledwor")
        gamePage.assertWordUncompleted()

        gamePage.typeLetter("d")
        gamePage.assertWordCompleted()

        gamePage.clickCheck()
        gamePage.assertCorrectWord()

        gamePage.clickNext()
        gamePage.assertInitialState("eNtxowdr")

    }


    /**
     * UG TestCase #2 "Wrong Path"
     */
    @Test
    fun caseNumber2() {

        gamePage.assertInitialState("cSarbmeldowdr")

        gamePage.typeLetter("S")
        gamePage.assertWordUncompleted()

        gamePage.typeLetter("rambledwodr")
        gamePage.assertWordUncompleted()

        gamePage.clickCheck()
        gamePage.assertErrorWord()

        gamePage.deleteOneLetter()
        gamePage.assertWordUncompleted()

        gamePage.typeLetter("Q")
        gamePage.assertWordCompleted()

        gamePage.clickCheck()
        gamePage.assertErrorWord()

        gamePage.clickNext()
        gamePage.assertInitialState("eNtxowdr")
    }


    /**
     * UG TestCase #3 "Other path"
     */
    @Test
    fun caseNumber3() {

        gamePage.assertInitialState("cSarbmeldowdr")

        gamePage.clickSkip()
        gamePage.assertInitialState("eNtxowdr")

        gamePage.typeLetter("N")
        gamePage.assertWordUncompleted()

        gamePage.clickSkip()
        gamePage.assertInitialState("eSocdnowdr")

        gamePage.typeLetter("S")
        gamePage.assertWordUncompleted()

        gamePage.typeLetter("econdword")
        gamePage.assertWordCompleted()

        gamePage.deleteOneLetter()
        gamePage.assertWordUncompleted()

        gamePage.deleteOneLetter()
        gamePage.assertWordUncompleted()

        gamePage.typeLetter("rd")
        gamePage.assertWordCompleted()

        gamePage.clickSkip()
        gamePage.assertInitialState("hTriwdrod")
    }

}

/*
Scrambledword
Nextword
Secondword
Thirdword
Anotherword
* */