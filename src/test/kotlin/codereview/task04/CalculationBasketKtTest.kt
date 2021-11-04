package codereview.task04

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import java.util.*


internal class CalculationBasketKtTest {

    @ParameterizedTest
    @ValueSource(strings = [
        "0, 0, 0, 0, 0: 0.00",
        "0, 1, 0, 0, 0: 8.00",
        "0, 1, 0, 1, 0: 15.20",
        "1, 0, 1, 1, 0: 21.60",
        "1, 0, 1, 1, 1: 25.60",
        "1, 1, 1, 1, 1: 30.00",
        "3, 2, 1, 1, 1: 53.20",
        "1, 2, 2, 1, 1: 45.20",
        "2, 1, 2, 2, 1: 51.20",
        "3, 7, 9, 6, 4: 189.20",
        "9, 8, 1, 1, 7: 182.40"
    ])
    fun total(values: String) {
        val (itemsString, expected) = values.split(": ").asPair { first, second ->
            Pair(first, second.replace(".", ","))
        }
        val items = itemsString.split(", ").map { it.toInt() }

        Assertions.assertEquals(expected, "%.2f".format(Locale.getDefault(), total(items)))
    }
}