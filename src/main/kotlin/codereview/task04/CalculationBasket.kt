package codereview.task04

/** Входные данные (Заполненная корзина) */
var cart = listOf(2, 2, 2, 1, 1)

/** Мапа со скидками */
val discount = mapOf(
    5 to 0.25f,
    4 to 0.20f,
    3 to 0.10f,
    2 to 0.05f
)

/** Цена одного товара */
const val price = 8

/**
 * Подсчет стоимости корзины с учетом скидок указанных в [discount]
 */
fun total(items: List<Int>): Float {
    val groupingCart = mutableListOf<Int>()
    var sizeGroup = sizeGroup(items)
    var copyItems = items.toList()
    var total = 0f

    do {
        groupingCart.add(sizeGroup)
        copyItems = sliceItems(copyItems)
        sizeGroup = sizeGroup(copyItems)
    } while (sizeGroup > 0)

    groupingCart.forEach {
        if (it in discount.keys) {
            total += (price - price * discount[it] as Float) * it
        } else {
            total += price
        }
    }

    return total
}

/**
 * Срезаем "верхушку" с корзины.
 * Далее полученный список используется для подсчета элементов.
 */
fun sliceItems(items: List<Int>) = items.map { it - 1 }

/**
 * Подсчет количества товаров в корзине
 */
fun sizeGroup(items: List<Int>) = items.filter { it > 0 }.size