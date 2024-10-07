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
        var expected: GameUiState = GameUiState.Initial(scrambledWord = "1f")
        assertEquals(expected, actual)

        actual = viewModel.handleInputText(userText = "f")
        expected = GameUiState.WordUncompleted(scrambledWord = "1f")
        assertEquals(expected, actual)

        actual = viewModel.handleInputText(userText = "f1")
        expected = GameUiState.WordCompleted(scrambledWord = "1f")
        assertEquals(expected, actual)

        actual = viewModel.onCheck(userText = "f1")
        expected = GameUiState.CorrectWord(scrambledWord = "1f")
        assertEquals(expected, actual)

        actual = viewModel.onNext()
        expected = GameUiState.Initial(scrambledWord = "2f")
        assertEquals(expected, actual)
    }


    /**
     * UG TestCase #2 "Wrong Path"
     */
    @Test
    fun caseNumber2() {
        var actual: GameUiState = viewModel.init()
        var expected: GameUiState = GameUiState.Initial(scrambledWord = "1f")
        assertEquals(expected, actual)

        actual = viewModel.handleInputText(userText = "f")
        expected = GameUiState.WordUncompleted(scrambledWord = "1f")
        assertEquals(expected, actual)

        actual = viewModel.handleInputText(userText = "f2")
        expected = GameUiState.WordCompleted(scrambledWord = "1f")
        assertEquals(expected, actual)

        actual = viewModel.onCheck(userText = "f2")
        expected = GameUiState.ErrorWord(scrambledWord = "1f")
        assertEquals(expected, actual)

        actual = viewModel.handleInputText(userText = "f")
        expected = GameUiState.WordUncompleted(scrambledWord = "1f")
        assertEquals(expected, actual)

        actual = viewModel.handleInputText(userText = "f3")
        expected = GameUiState.WordCompleted(scrambledWord = "1f")
        assertEquals(expected, actual)

        actual = viewModel.onCheck(userText = "f3")
        expected = GameUiState.ErrorWord(scrambledWord = "1f")
        assertEquals(expected, actual)

        actual = viewModel.onSkip()
        expected = GameUiState.Initial(scrambledWord = "2f")
        assertEquals(expected, actual)

    }

    /**
     * UG TestCase #3 "Other"
     */
    @Test
    fun caseNumber3() {
        var actual: GameUiState = viewModel.init()
        var expected: GameUiState = GameUiState.Initial(scrambledWord = "1f")
        assertEquals(expected, actual)

        actual = viewModel.onSkip()
        expected = GameUiState.Initial(scrambledWord = "2f")
        assertEquals(expected, actual)

        actual = viewModel.handleInputText(userText = "f")
        expected = GameUiState.WordUncompleted(scrambledWord = "2f")
        assertEquals(expected, actual)

        actual = viewModel.onSkip()
        expected = GameUiState.Initial(scrambledWord = "3f")
        assertEquals(expected, actual)

        actual = viewModel.handleInputText(userText = "f")
        expected = GameUiState.WordUncompleted(scrambledWord = "3f")
        assertEquals(expected, actual)

        actual = viewModel.handleInputText(userText = "f3")
        expected = GameUiState.WordCompleted(scrambledWord = "3f")
        assertEquals(expected, actual)

        actual = viewModel.handleInputText(userText = "f")
        expected = GameUiState.WordUncompleted(scrambledWord = "3f")
        assertEquals(expected, actual)

        actual = viewModel.handleInputText(userText = "f3")
        expected = GameUiState.WordCompleted(scrambledWord = "3f")
        assertEquals(expected, actual)

        actual = viewModel.onSkip()
        expected = GameUiState.Initial(scrambledWord = "4f")
        assertEquals(expected, actual)
    }
}



private class FakeRepository : GameRepository {

    private val list = listOf("f1", "f2", "f3", "f4", "f5", "f6")

    private var index: Int = 0

    override fun scrambledWord(): String = list[index].reversed()

    override fun currentWord(): String = list[index]

    override fun next() {
        index++
        if (index == list.size) {
            index = 0
        }
        input = ""
    }

    private var input: String = ""

    override fun userInput(): String {
        return input
    }

    override fun saveUserInput(value: String) {
        input = value
    }

    override fun completed(userWord: String): Boolean = userWord.length == list[index].length

    override fun sameWord(userWord: String): Boolean = list[index] == userWord

}