package December_1_2015

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test


class ElevatorKtTest {

    val data = ("()(((())") // 5 '(' 3 ')'
    val data2 = ("()(((()))))") // 5 '(' 6 ')'
    val parenthesisLeft = 5
    val parenthesisRight = 3

    @Test
    fun countLeftParenthesisTest() {
        Assertions.assertEquals(countLeftParenthesis(data), 5)
        Assertions.assertFalse(countLeftParenthesis(data) == 3)
        Assertions.assertFalse(countLeftParenthesis(data) == 8)
    }

    @Test
    fun countRightParenthesisTest() {
        Assertions.assertEquals(countRightParenthesis(data), 3)
        Assertions.assertFalse(countRightParenthesis(data) == 5)
        Assertions.assertFalse(countRightParenthesis(data) == 8)
    }

    @Test
    fun getFloorNumberByComparingTest() {
        Assertions.assertTrue(getFloorNumberByComparing(parenthesisLeft, parenthesisRight) == 2)
        Assertions.assertFalse(getFloorNumberByComparing(parenthesisLeft, parenthesisRight) == -2)
    }

    @Test
    fun getFirstPositionOfBasementTest() {
        Assertions.assertTrue(getFirstPositionOfBasement(data2) == 11)
        Assertions.assertFalse(getFirstPositionOfBasement(data2) == 10)
    }
}