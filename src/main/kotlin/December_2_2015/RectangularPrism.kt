package December_2_2015

class RectangularPrism(
    val length: Int, val width: Int, val height: Int,
    val listOfSideMeasurements: List<Int> = listOf(length, width, height).sorted(),
    private val sideA: Int = length * width, private val sideB: Int = width * height, private val sideC: Int = length * height,
    val listOfSides: List<Int> = listOf(sideA, sideB, sideC).sorted(),
    val area: Int = 2 * sideA + 2 * sideB + 2 * sideC
)