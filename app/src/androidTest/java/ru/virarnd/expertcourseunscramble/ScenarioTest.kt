package ru.virarnd.expertcourseunscramble

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ScenarioTest {

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    private lateinit var gamePage: GamePage

    @Before
    fun setup() {
        val gamePage = GamePage(scrambledWord = "cSarbmeldowdr")
    }

    /**
     * UG TestCase #1 "Happy Path"
     */

    @Test
    fun caseNumber1() {

        gamePage.assertInitialState("cSarbmeldowdr")

        gamePage.letterTypde("S")
        gamePage.assertWordUncompleted("S")

        gamePage.letterTypde("c")
        gamePage.assertWordUncompleted("Sc")

        gamePage.letterTypde("rambledwor")
        gamePage.assertWordUncompleted("Scrambledwor")

        gamePage.letterTypde("d")
        gamePage.assertWordCompleted("Scrambledword")

        gamePage.clickCheck()
        gamePage.assertCorrectWord("Scrambledword")

        gamePage.clickNext()
        gamePage.assertInitialState("eNtxowdr")

    }


    /**
     * UG TestCase #2 "Wrong Path"
     */
    @Test
    fun caseNumber2() {

        gamePage.assertInitialState("cSarbmeldowdr")

        gamePage.letterTypde("S")
        gamePage.assertWordUncompleted("S")

        gamePage.letterTypde("rambledwodr")
        gamePage.assertWordUncompleted("Scrambledwodr")

        gamePage.clickCheck()
        gamePage.assertErrorWord("Scrambledwodr")

        gamePage.deleteOneLetter()
        gamePage.assertWordUncompleted("Scrambledwod")

        gamePage.letterTypde("Q")
        gamePage.assertWordCompleted("ScrambledwodQ")

        gamePage.clickCheck()
        gamePage.assertErrorWord("ScrambledwodQ")

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

        gamePage.letterTypde("N")
        gamePage.assertWordUncompleted("N")

        gamePage.clickSkip()
        gamePage.assertInitialState("eSocdnowdr")

        gamePage.letterTypde("S")
        gamePage.assertWordUncompleted("S")

        gamePage.letterTypde("econdword")
        gamePage.assertWordCompleted("Secondword")

        gamePage.deleteOneLetter()
        gamePage.assertWordUncompleted("Secondwor")

        gamePage.deleteOneLetter()
        gamePage.assertWordUncompleted("Secondwo")

        gamePage.letterTypde("rd")
        gamePage.assertWordCompleted("Secondword")

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