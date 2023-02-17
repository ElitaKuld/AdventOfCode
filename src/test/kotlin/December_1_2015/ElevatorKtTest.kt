package December_1_2015

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test


class ElevatorKtTest {

    val listOfData = listOf('(', ')', '(', '(', '(', '(', ')', ')') // 5 '(' 3 ')'
    val listOfData2 = listOf('(', ')', '(', '(', '(', '(', ')', ')', ')', ')', ')') // 5 '(' 6 ')'
    val parenthesisLeft = 5
    val parenthesisRight = 3

    @Test
    fun countLeftParenthesisTest() {
        Assertions.assertEquals(countLeftParenthesis(listOfData), 5)
        Assertions.assertFalse(countLeftParenthesis(listOfData) == 3)
        Assertions.assertFalse(countLeftParenthesis(listOfData) == 8)
    }

    @Test
    fun countRightParenthesisTest() {
        Assertions.assertEquals(countRightParenthesis(listOfData), 3)
        Assertions.assertFalse(countRightParenthesis(listOfData) == 5)
        Assertions.assertFalse(countRightParenthesis(listOfData) == 8)
    }

    @Test
    fun getFloorNumberByComparingTest() {
        Assertions.assertTrue(getFloorNumberByComparing(parenthesisLeft, parenthesisRight) == 2)
        Assertions.assertFalse(getFloorNumberByComparing(parenthesisLeft, parenthesisRight) == -2)
    }

    @Test
    fun getFirstPositionOfBasementTest() {
        Assertions.assertTrue(getFirstPositionOfBasement(listOfData2) == 11)
        Assertions.assertFalse(getFirstPositionOfBasement(listOfData2) == 10)
    }
}