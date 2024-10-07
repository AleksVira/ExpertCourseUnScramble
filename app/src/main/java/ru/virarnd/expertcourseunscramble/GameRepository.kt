package ru.virarnd.expertcourseunscramble

interface GameRepository {

    fun scrambledWord(): String
    fun currentWord(): String
    fun sameWord(userWord: String): Boolean
    fun completed(userWord: String): Boolean
    fun next()
    fun userInput(): String
    fun saveUserInput(value: String)

    class Base(
        private val index: IntCache,
        private val userInput: StringCache,
        private val shuffleStrategy: ShuffleStrategy = ShuffleStrategy.Reverse(),
        private val originalList: List<String> = listOf(
            "Nature", "Apartment", "Writing", "Homework", "Cabinet", "Revenue", "Concept", "Control", "Emotion", "Memory"
        )
    ) : GameRepository {

        override fun scrambledWord(): String = shuffleStrategy.shuffle(originalList[index.read()])

        override fun currentWord(): String = originalList[index.read()]

        override fun sameWord(userWord: String): Boolean = originalList[index.read()] == userWord

        override fun completed(userWord: String): Boolean = userWord.length == originalList[index.read()].length

        override fun next() {
            val value = index.read()
            if (value + 1 == originalList.size) {
                index.save(0)
            } else {
                index.save(value + 1)
            }
            userInput.save("")
        }

        override fun userInput(): String {
            return userInput.read()
        }

        override fun saveUserInput(value: String) {
            userInput.save(value)
        }
    }
}

interface ShuffleStrategy {
    fun shuffle(source: String): String

    class Base : ShuffleStrategy {
        override fun shuffle(source: String): String {
            return source.toCharArray().toList().shuffled().joinToString(separator = "")
        }
    }

    class Reverse : ShuffleStrategy {
        override fun shuffle(source: String): String {
            return source.reversed()
        }
    }
}