package ru.virarnd.expertcourseunscramble

class GameViewModel(private val repository: GameRepository) {

    fun init(): GameUiState {
        return GameUiState.Initial(repository.wordAndVariant())
    }

    fun onCheck(userText: String): GameUiState {
        val isCorrectWord = repository.sameWord(userText)
        return when {
            isCorrectWord -> GameUiState.CorrectWord(userText)
            else -> GameUiState.ErrorWord(userText)
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
        val isCompleted = repository.completed(userText)
        return when {
            isCompleted -> GameUiState.WordCompleted(userText)
            else -> GameUiState.WordUncompleted(userText)
        }
    }

}
