package December_2_2015

class RectangularPrism(
    val length: Int, val width: Int, val height: Int,
    val sideA: Int = length * width, val sideB: Int = width * height, val sideC: Int = length * height,
    val area: Int = 2 * sideA + 2 * sideB + 2 * sideC
)

//Area: 2*l*w + 2*w*h + 2*h*l