package December_5_2015

import java.io.File

fun solvePartOne(input: String) = input.lineSequence()
    .filter { string -> string.count { it in "aeiou" } >= 3 }
    .filter { string -> string.zipWithNext().any { it.first == it.second } }
    .filterNot { "ab" in it || "cd" in it || "pq" in it || "xy" in it }
    .count()


fun solvePartTwo(input: String) = input
    .lineSequence()
    .filter { string -> string.windowed(3).any { it[0] == it[2] } }
    .filter { string ->
        string.windowedSequence(2)
            .withIndex()
            .any { (i, v) -> v in string.substring(i + 2) }
    }
    .count()


fun main(){
    val dataToWorkWith = File("src/main/kotlin/December_5_2015/data.txt").readText() // String
    println(solvePartOne(dataToWorkWith))
    println(solvePartTwo(dataToWorkWith))
}