package ru.virarnd.expertcourseunscramble

interface GameRepository {

    fun wordAndVariant(): WordAndScrambledWord

    fun sameWord(userWord: String): Boolean

    fun completed(userWord: String): Boolean

    fun next()
}
