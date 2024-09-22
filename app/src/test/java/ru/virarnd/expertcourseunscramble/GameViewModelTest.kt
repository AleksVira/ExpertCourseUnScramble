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
        var expected: GameUiState = GameUiState.Initial(
            WordAndScrambledWord(
                correctWord = "f1", scrambledWord = "1f"
            )
        )
        assertEquals(expected, actual)

        actual = viewModel.handleInputText(userText = "f")
        expected = GameUiState.WordUncompleted(userText = "f")
        assertEquals(expected, actual)

        actual = viewModel.handleInputText(userText = "f1")
        expected = GameUiState.WordCompleted(userText = "f1")
        assertEquals(expected, actual)

        actual = viewModel.onCheck(userText = "f1")
        expected = GameUiState.CorrectWord(userText = "f1")
        assertEquals(expected, actual)

        actual = viewModel.onNext()
        expected = GameUiState.Initial(
            WordAndScrambledWord(
                correctWord = "f2", scrambledWord = "2f"
            )
        )
        assertEquals(expected, actual)
    }


    /**
     * UG TestCase #2 "Wrong Path"
     */
    @Test
    fun caseNumber2() {
        var actual: GameUiState = viewModel.init()
        var expected: GameUiState = GameUiState.Initial(
            WordAndScrambledWord(
                correctWord = "f1", scrambledWord = "1f"
            )
        )
        assertEquals(expected, actual)

        actual = viewModel.handleInputText(userText = "f")
        expected = GameUiState.WordUncompleted(userText = "f")
        assertEquals(expected, actual)

        actual = viewModel.handleInputText(userText = "f2")
        expected = GameUiState.WordCompleted(userText = "f2")
        assertEquals(expected, actual)

        actual = viewModel.onCheck(userText = "f2")
        expected = GameUiState.ErrorWord(userText = "f2")
        assertEquals(expected, actual)

        actual = viewModel.handleInputText(userText = "f")
        expected = GameUiState.WordUncompleted(userText = "f")
        assertEquals(expected, actual)

        actual = viewModel.handleInputText(userText = "f3")
        expected = GameUiState.WordCompleted(userText = "f3")
        assertEquals(expected, actual)

        actual = viewModel.onCheck(userText = "f3")
        expected = GameUiState.ErrorWord(userText = "f3")
        assertEquals(expected, actual)

        actual = viewModel.onSkip()
        expected = GameUiState.Initial(
            WordAndScrambledWord(
                correctWord = "f2", scrambledWord = "2f"
            )
        )
        assertEquals(expected, actual)

    }

    /**
     * UG TestCase #3 "Other"
     */
    @Test
    fun caseNumber3() {
        var actual: GameUiState = viewModel.init()
        var expected: GameUiState = GameUiState.Initial(
            WordAndScrambledWord(
                correctWord = "f1", scrambledWord = "1f"
            )
        )
        assertEquals(expected, actual)

        actual = viewModel.onSkip()
        expected = GameUiState.Initial(
            WordAndScrambledWord(
                correctWord = "f2", scrambledWord = "2f"
            )
        )
        assertEquals(expected, actual)

        actual = viewModel.handleInputText(userText = "f")
        expected = GameUiState.WordUncompleted(userText = "f")
        assertEquals(expected, actual)

        actual = viewModel.onSkip()
        expected = GameUiState.Initial(
            WordAndScrambledWord(
                correctWord = "f3", scrambledWord = "3f"
            )
        )
        assertEquals(expected, actual)

        actual = viewModel.handleInputText(userText = "f")
        expected = GameUiState.WordUncompleted(userText = "f")
        assertEquals(expected, actual)

        actual = viewModel.handleInputText(userText = "f3")
        expected = GameUiState.WordCompleted(userText = "f3")
        assertEquals(expected, actual)

        actual = viewModel.handleInputText(userText = "f")
        expected = GameUiState.WordUncompleted(userText = "f")
        assertEquals(expected, actual)

        actual = viewModel.handleInputText(userText = "f3")
        expected = GameUiState.WordCompleted(userText = "f3")
        assertEquals(expected, actual)

        actual = viewModel.onSkip()
        expected = GameUiState.Initial(
            WordAndScrambledWord(
                correctWord = "f4", scrambledWord = "4f"
            )
        )
        assertEquals(expected, actual)
    }
}


private class FakeRepository : GameRepository {

    //    private val list = listOf("Nature", "Apartment", "Writing", "Homework", "Cabinet", "Revenue", "Concept", "Control", "Emotion", "Memory")
    private val list = listOf("f1", "f2", "f3", "f4", "f5", "f6")

    private var index: Int = 0

    private var userWord: String = ""

    override fun wordAndVariant() = WordAndScrambledWord(
        correctWord = list[index], scrambledWord = list[index].reversed()
    )

    override fun next() {
        userWord = ""
        index++
        if (index == list.size) {
            index = 0
        }
    }

    /*
        override fun saveUserVariant(userWord: String) {
            this.userWord = userWord
        }

        override fun getCorrectWord(): String {
            return list[index]
        }
    */

    override fun completed(userWord: String): Boolean = userWord.length == list[index].length


    override fun sameWord(userWord: String): Boolean = list[index] == userWord


}