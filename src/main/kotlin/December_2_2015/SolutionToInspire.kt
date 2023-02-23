package December_2_2015

// Ett exempel på kod som ska lära oss en bättre lösning:
// https://github.com/Ruud-Wiegers/advent-of-code/blob/master/y2015/src/main/kotlin/adventofcode/y2015/Day02.kt
fun solvePartOne(input: String) =
    toListOfPresents(input).sumOf { 3 * it[0] * it[1] + 2 * it[1] * it[2] + 2 * it[2] * it[0] }
        .toString()

fun solvePartTwo(input: String) =
    toListOfPresents(input).sumOf { 2 * (it[0] + it[1]) + it[0] * it[1] * it[2] }
        .toString()

private fun toListOfPresents(input: String): List<List<Int>> = input.lines()
    .filter(String::isNotBlank)
    .map { it.split("x").map(String::toInt).sorted() }