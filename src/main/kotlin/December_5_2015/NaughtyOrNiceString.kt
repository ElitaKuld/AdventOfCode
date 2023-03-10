package December_5_2015

import java.io.File
import java.util.*

// Extension Funktion:
fun Char.isVowel(): Boolean = this == 'a' || this == 'e' || this == 'i' || this == 'o' || this == 'u'


// Extension Funktion:
fun String.containsMinimumThreeVowels(): Boolean {
    val numberOfVowels = mutableListOf<Char>() // to avoid creating mutable Int
    for (i in this.indices) {
        if (this[i].isVowel()) numberOfVowels.add(this[i])
    }
    return numberOfVowels.size >= 3
}

fun String.sameLetterTwiceInARow(): Boolean {
    var sameLetter = false
    for (i in 0 until this.lastIndex) {
        if (this[i] == this[i + 1])
            sameLetter = true
    }
    return sameLetter
}

fun String.vipOnly(): Boolean {
    return !this.contains("ab") && !this.contains("cd") && !this.contains("pq") && !this.contains("xy")
}

fun String.isNice(): Boolean {
    return this.containsMinimumThreeVowels() && this.sameLetterTwiceInARow() && this.vipOnly()
}

fun String.isNiceNewWay(): Boolean {
    return this.sameLetterWithALetterInBetween() && !this.overlaps() && this.containsPairOfTwoLetters()
}

fun String.sameLetterWithALetterInBetween(): Boolean {
    var sameLetterWithALetter = false
    for (i in 1 until this.lastIndex) {
        if (this[i - 1] == this[i + 1])
            sameLetterWithALetter = true
    }
    return sameLetterWithALetter
}

fun String.overlaps(): Boolean {
    var overlaps = false
    for (i in 0 until this.lastIndex - 1) {
        if (this[i] == this[i + 1] && this[i] == this[i + 2] && !this.containsPairOfTwoLetters())
            overlaps = true
    }
    return overlaps
}

fun String.containsPairOfTwoLetters(): Boolean {
    var containsPair = false
    for (i in 0 until this.lastIndex - 2) {
        var pairToCheck: String = this.substring(i, i + 2)
        if (this.substring(i + 2, this.length).contains(pairToCheck))
            containsPair = true
    }
    return containsPair
}

fun readFileToList(pathname: String): List<String> {
    val scanner = Scanner(File(pathname))
    val list = mutableListOf<String>()
    while (scanner.hasNextLine()) {
        list.add(scanner.nextLine())
    }
    return list
}

// En "After-funktion" DEl A:
fun countNiceStrings(dataToWorkWith: String) = dataToWorkWith.lineSequence()
    .filter { string -> string.count { it in "aeiou" } >= 3 }
    .filter { string -> string.zipWithNext().any { it.first == it.second } }
    .filterNot { "ab" in it || "cd" in it || "pq" in it || "xy" in it }
    .count()

// En "After-funktion" DEl B:
fun countNiceStringsNewWay(dataToWorkWith: String) = dataToWorkWith
    .lineSequence()
    .filter { string -> string.windowed(3).any { it[0] == it[2] } }
    .filter { string ->
        string.windowedSequence(2)
            .withIndex()
            .any { (i, v) -> v in string.substring(i + 2) }
    }
    .count()

fun main() {

    val filePathname = "src/main/kotlin/December_5_2015/data.txt"
    val listOfData = readFileToList(filePathname) // read data into a list

    // Del A:
    val niceStringList = mutableListOf<String>()
    for (i in 0..listOfData.lastIndex) if (listOfData[i].isNice()) niceStringList.add(listOfData[i])
    println("How many strings are nice?: " + niceStringList.size)
    // CORRECT!!!

    // Del B:
    val niceStringListPartTwo = mutableListOf<String>()
    for (i in 0..listOfData.lastIndex) if (listOfData[i].isNiceNewWay()) niceStringListPartTwo.add(listOfData[i])
    println("How many strings are nice under these new rules?: " + niceStringListPartTwo.size)

    // not 50
    // 51 is the right answer!

    //------------------------------Solution After-----------------------------------------------------
    // En annan l??sning med hj??lp av koden fr??n f??ljande repo: https://github.com/Ruud-Wiegers/advent-of-code/blob/master/y2015/src/main/kotlin/adventofcode/y2015/Day05.kt
    // Del A:
    val dataToWorkWith = File("src/main/kotlin/December_5_2015/data.txt").readText() // String
    println("How many strings are nice?: " + countNiceStrings(dataToWorkWith))
    // Del B:
    println("How many strings are nice under these new rules?: " + countNiceStringsNewWay(dataToWorkWith))
}

