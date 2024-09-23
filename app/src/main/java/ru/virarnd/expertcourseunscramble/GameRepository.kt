package ru.virarnd.expertcourseunscramble

interface GameRepository {

    fun scrambledWord(): String

    fun currentWord(): String

    fun sameWord(userWord: String): Boolean

    fun completed(userWord: String): Boolean

    fun next()

    class Base(
        private val shuffleStrategy: ShuffleStrategy = ShuffleStrategy.Reverse(),
        private val originalList: List<String> = listOf(
            "Nature", "Apartment", "Writing", "Homework", "Cabinet", "Revenue", "Concept", "Control", "Emotion", "Memory"
        )
    ) : GameRepository {

        private var index: Int = 0

        override fun scrambledWord(): String = shuffleStrategy.shuffle(originalList[index])

        override fun currentWord(): String = originalList[index]

        override fun sameWord(userWord: String): Boolean = originalList[index] == userWord

        override fun completed(userWord: String): Boolean = userWord.length == originalList[index].length

        override fun next() {
            index++
            if (index == originalList.size) {
                index = 0
            }
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