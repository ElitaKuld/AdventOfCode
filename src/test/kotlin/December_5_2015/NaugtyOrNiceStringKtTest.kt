package December_5_2015

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class NaughtyOrNiceStringKtTest {
    val filePathname = "src/main/kotlin/December_5_2015/testdata.txt"

    val char = 'a'
    val char2 = 'b'

    val string = "aei"
    val string2 = "xazegov"
    val string3 = "aeiouaeiouaeiou"
    val string4 = "wrtypsdfghklz"

    val string5 = "abcdde"
    val string6 = "aabbccdd"

    val string7 = "abcdpqxy"
    val string8 = "fgklrsz"

    val string9 = "ugknbfddgicrmopn"
    val string10 = "aaa"
    val string11 = "jchzalrnumimnmhp"
    val string12 = "haegwjzuvuyypxyu"
    val string13 = "dvszwmarrgswjxmb"

    val string14 = "xyx"
    val string15 = "abcdefeghi"
    val string16 = "aaa"
    val string17 = "fghjklas"

    val string18 = "aabcdefgaa"
    val string19 = "xyxy"
    val string20 = "xyuiopåby"

    val string21 = "qjhvhtzxzqqjkmpb"
    val string22 = "xxyxx"
    val string23 = "uurcxstgmygtbstg"
    val string24 = "ieodomkazucvgmuy"

    @Test
    fun readFileToListTest() {
        Assertions.assertEquals(December_2_2015.readFileToList(filePathname).size, 5)
        Assertions.assertTrue(December_2_2015.readFileToList(filePathname)[0] == "uxcplgxnkwbdwhrp")
        Assertions.assertTrue(December_2_2015.readFileToList(filePathname)[1] == "suerykeptdsutidb")
        Assertions.assertTrue(December_2_2015.readFileToList(filePathname)[2] == "dmrtgdkaimrrwmej")
        Assertions.assertTrue(December_2_2015.readFileToList(filePathname)[3] == "ztxhjwllrckhakut")
        Assertions.assertTrue(December_2_2015.readFileToList(filePathname)[4] == "gdnzurjbbwmgayrg")
    }

    @Test
    fun isVowelTest() {
        Assertions.assertTrue(char.isVowel())
        Assertions.assertFalse(char2.isVowel())
    }

    @Test
    fun containsMinimumThreeVowelsTest() {
        Assertions.assertTrue(string.containsMinimumThreeVowels())
        Assertions.assertTrue(string2.containsMinimumThreeVowels())
        Assertions.assertTrue(string3.containsMinimumThreeVowels())
        Assertions.assertFalse(string4.containsMinimumThreeVowels())
    }

    @Test
    fun isNiceTest() {
        Assertions.assertTrue(string9.isNice())
        Assertions.assertTrue(string10.isNice())
        Assertions.assertFalse(string11.isNice())
        Assertions.assertFalse(string12.isNice())
        Assertions.assertFalse(string13.isNice())
    }

    @Test
    fun sameLetterTwiceInARowTest() {
        Assertions.assertTrue(string5.sameLetterTwiceInARow())
        Assertions.assertTrue(string6.sameLetterTwiceInARow())
    }

    @Test
    fun vipOnlyTest() {
        Assertions.assertTrue(!string7.vipOnly())
        Assertions.assertTrue(string8.vipOnly())
    }

    @Test
    fun sameLetterWithALetterInBetweenTest() {
        Assertions.assertTrue(string14.sameLetterWithALetterInBetween())
        Assertions.assertTrue(string15.sameLetterWithALetterInBetween())
        Assertions.assertTrue(string16.sameLetterWithALetterInBetween())
        Assertions.assertFalse(string17.sameLetterWithALetterInBetween())
        Assertions.assertTrue("rxexcbwhiywwwwnu".sameLetterWithALetterInBetween())
    }

    @Test
    fun overlapsTest() {
        Assertions.assertTrue(string16.overlaps())
        Assertions.assertFalse(string18.overlaps())
        Assertions.assertFalse("rxexcbwhiywwwwnu".overlaps()) // Failed the test the first time!
    }

    @Test
    fun containsPairOfTwoLettersTest() {
        Assertions.assertTrue(string18.containsPairOfTwoLetters())
        Assertions.assertTrue(string19.containsPairOfTwoLetters())
        Assertions.assertFalse(string20.containsPairOfTwoLetters())
        Assertions.assertTrue("rxexcbwhiywwwwnu".containsPairOfTwoLetters())
    }

    @Test
    fun isNiceNewWayTest() {
        Assertions.assertTrue(string21.isNiceNewWay())
        Assertions.assertTrue(string22.isNiceNewWay())
        Assertions.assertFalse(string23.isNiceNewWay())
        Assertions.assertFalse(string24.isNiceNewWay())
        Assertions.assertTrue("rxexcbwhiywwwwnu".isNiceNewWay())
    }
}

/*
qjhvhtzxzqqjkmpb is nice because is has a pair that appears twice (qj) and a letter that repeats with exactly one letter between them (zxz).
xxyxx is nice because it has a pair that appears twice and a letter that repeats with one between, even though the letters used by each rule overlap.
uurcxstgmygtbstg is naughty because it has a pair (tg) but no repeat with a single letter between them.
ieodomkazucvgmuy is naughty because it has a repeating letter with one between (odo), but no pair that appears twice.
 */
/*
    val string21 = "qjhvhtzxzqqjkmpb"
    val string22 = "xxyxx"
    val string23 = "uurcxstgmygtbstg"
    val string24 = "ieodomkazucvgmuy"
 */