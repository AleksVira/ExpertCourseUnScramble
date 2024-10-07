package ru.virarnd.expertcourseunscramble

class GameViewModel(private val repository: GameRepository) {

    fun init(): GameUiState {
        return GameUiState.Initial(repository.scrambledWord(), repository.userInput())
    }

    fun onCheck(userText: String): GameUiState {
        val isCorrectWord = repository.sameWord(userText)
        return when {
            isCorrectWord -> GameUiState.CorrectWord(repository.scrambledWord())
            else -> GameUiState.ErrorWord(repository.scrambledWord())
        }
    }

    fun onSkip(): GameUiState {
        repository.next()
        return init()
    }

    fun onNext(): GameUiState {
        repository.next()
        return init()
    }

    fun handleInputText(userText: String): GameUiState {
        repository.saveUserInput(userText)
        val isCompleted = repository.completed(userText)
        return when {
            isCompleted -> GameUiState.WordCompleted(repository.scrambledWord())
            else -> GameUiState.WordUncompleted(repository.scrambledWord())
        }
    }

}
