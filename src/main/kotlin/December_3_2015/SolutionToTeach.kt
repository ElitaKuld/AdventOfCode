package December_3_2015

// Ett exempel på kod som ska lära oss en bättre lösning:
// https://github.com/timakden/advent-of-code/blob/main/src/main/kotlin/ru/timakden/aoc/year2015/Day03.kt

object Day03 {

    fun part1(input: String): Int {
        var x = 0
        var y = 0

        return input.map {
            when (it) {
                '^' -> y++
                'v' -> y--
                '<' -> x--
                '>' -> x++
            }

            x to y
        }.plus(0 to 0).distinct().size
    }

    fun part2(input: String): Int {
        var santaX = 0
        var santaY = 0
        var robotX = 0
        var robotY = 0
        var counter = 0

        return input.map {
            val isCounterEven = counter % 2 == 0

            when (it) {
                '^' -> if (isCounterEven) santaY++ else robotY++
                'v' -> if (isCounterEven) santaY-- else robotY--
                '<' -> if (isCounterEven) santaX-- else robotX--
                '>' -> if (isCounterEven) santaX++ else robotX++
            }

            counter++

            if (isCounterEven) santaX to santaY
            else robotX to robotY
        }.plus(0 to 0).distinct().size
    }
}