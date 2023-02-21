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
/*
fun String.overlaps(): Boolean {
    var overlaps = false
    for (i in 0 until this.lastIndex-1) {
        if (this[i] == this[i + 1] && this[i] == this[i + 2])
            overlaps = true
    }
    return overlaps
}
 */

fun String.overlaps(): Boolean {
    var overlaps = false
    for (i in 0 until this.lastIndex-1) {
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

fun main() {

    val filePathname = "src/main/kotlin/December_5_2015/data.txt"
    val listOfData = readFileToList(filePathname) // read data into a list
    println(listOfData.size)
    println(listOfData)

    // Del A:
    val niceStringList = mutableListOf<String>()
    for (i in 0..listOfData.lastIndex) if (listOfData[i].isNice()) niceStringList.add(listOfData[i])
    println(niceStringList.size)
    // CORRECT!!!

    // Del B:
    val niceStringListPartTwo = mutableListOf<String>()
    for (i in 0..listOfData.lastIndex) if (listOfData[i].isNiceNewWay()) niceStringListPartTwo.add(listOfData[i])
    println(niceStringListPartTwo.size)
}

// not 50
// 51 is the answer