package December_5_2015

// Kod som ska lära oss en bättre lösning:
// https://github.com/Ruud-Wiegers/advent-of-code/blob/master/y2015/src/main/kotlin/adventofcode/y2015/Day05.kt


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