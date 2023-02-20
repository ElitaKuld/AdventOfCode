package December_3_2015

// https://github.com/jbristow/adventofcode/tree/master/aoc2015/kotlin/src/main/kotlin

import java.nio.file.Files
import java.nio.file.Path
import kotlin.streams.asSequence

abstract class AdventOfCode() {
    private val fileName: String = "src/main/kotlin/December_3_2015/data.txt"
    private val file: Path = Path.of(fileName)

    val inputFileLineSequence: Sequence<String>
        get() = Files.lines(Path.of(fileName)).asSequence()
    val inputFileLines: List<String>
        get() = Files.readAllLines(Path.of(fileName))
    val inputFile: String
        get() = Files.readString(Path.of(fileName))
}

data class Point2d(val x: Int, val y: Int)

operator fun Point2d.plus(other: Point2d) =
    Point2d(x = this.x + other.x, y = this.y + other.y)

object Day03 : AdventOfCode() {

    @JvmStatic
    fun main(args: Array<String>) {
        println("Day03:")
        println("\tPart 1: ${part1(inputFile)}")
        println("\tPart 2: ${part2(inputFile)}")
    }

    sealed class Direction(val delta: Point2d) {
        object North : Direction(Point2d(0, 1))
        object South : Direction(Point2d(0, -1))
        object East : Direction(Point2d(1, 0))
        object West : Direction(Point2d(-1, 0))
    }

    private fun Char.toDirection(): Direction {
        return when (this) {
            '^' -> Direction.North
            'v' -> Direction.South
            '>' -> Direction.East
            '<' -> Direction.West
            else -> throw IllegalArgumentException("Unknown direction $this")
        }
    }

    private fun part1(input: String): Int {
        return input.map { it.toDirection() }
            .runningFold(Point2d(0, 0)) { acc, it -> acc + it.delta }
            .distinct()
            .count()
    }

    private fun part2(input: String) =
        input.asSequence()
            .map { it.toDirection() }
            .chunked(2)
            .runningFold(listOf(Point2d(0, 0), Point2d(0, 0))) { (santa, robot), (santaDir, robotDir) ->
                listOf(santa + santaDir.delta, robot + robotDir.delta)
            }.flatten()
            .distinct()
            .count()
}