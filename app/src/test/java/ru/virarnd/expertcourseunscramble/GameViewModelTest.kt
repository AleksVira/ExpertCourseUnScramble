package ru.virarnd.expertcourseunscramble

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GameViewModelTest {

    private lateinit var viewModel: GameViewModel

    @Before
    fun setup() {
        viewModel = GameViewModel(repository = FakeRepository())
    }

    /**
     * UG TestCase #1 "Happy Path"
     */

    @Test
    fun caseNumber1() {

        var actual: GameUiState = viewModel.init()
        var expected: GameUiState = GameUiState.Initial(scrambledWord = "f1")
        assertEquals(expected, actual)

        actual = viewModel.handleInputText(text = "f")
        expected = GameUiState.WordUncompleted(scrambledWord = "f1")
        assertEquals(expected, actual)

        actual = viewModel.handleInputText(text = "f1")
        expected = GameUiState.WordCompleted(scrambledWord = "f1")
        assertEquals(expected, actual)

        actual = viewModel.onCheck(text = "f1")
        expected = GameUiState.CorrectWord(scrambledWord = "f1")
        assertEquals(expected, actual)

        actual = viewModel.onNext()
        expected = GameUiState.Initial(scrambledWord = "f2")
        assertEquals(expected, actual)

    }


    /**
     * UG TestCase #2 "Wrong Path"
     */
    @Test
    fun caseNumber2() {

    }

}


private class FakeRepository : GameRepository() {

    private val list = listOf("Nature", "Apartment", "Writing", "Homework", "Cabinet", "Revenue", "Concept", "Control", "Emotion", "Memory")

    private var index: Int = 0

    private var userWord: String = ""

    override fun wordAndVariant() = WordAndVariant(
        correctWord = list[index],
        scrumbledWord = list[index].toCharArray().shuffle().toString()
    )

    override fun nextWord() {
        userWord = ""
        index++
        if (index == list.size) {
            index = 0
        }
    }

    override fun saveUserVariant(userWord: String) {
        this.userWord = userWord
    }

    override fun getCorrectWord(): String {
        return list[index]
    }


}