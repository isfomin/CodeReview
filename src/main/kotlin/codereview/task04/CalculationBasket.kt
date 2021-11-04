package codereview.task04

/** Входные данные (Заполненная корзина) */
var cart = listOf(2, 2, 1, 2, 1)

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
 *  Генерация комбинаций товаров в корзине
 */
fun generatePossibleGroups(inputGroup: List<Int>): Map<Int, List<Int>> {
    val mapPossibleGroups = mutableMapOf<Int, List<Int>>()
    val copyInputGroup = inputGroup.toMutableList()
    val minElementFirst = copyInputGroup.minAndRemove()
    val minElementSecond = copyInputGroup.minAndRemove()

    if (minElementFirst != null && minElementSecond != null) {
        for (item in 0..minElementFirst) {
            val partInputWithoutMinimumElements = copyInputGroup.toMutableList()
            partInputWithoutMinimumElements.add(item)
            partInputWithoutMinimumElements.add(minElementSecond + minElementFirst - item)
            mapPossibleGroups[item] = partInputWithoutMinimumElements
        }
    } else {
        mapPossibleGroups[0] = inputGroup
    }

    return mapPossibleGroups
}

/**
 * Подсчет стоимости корзины для множества комбинаций товаров в корзине полученные в [generatePossibleGroups]
 */
fun total(items: List<Int>): Float {
    val possibleGroupsItems = generatePossibleGroups(items)
    val totals = mutableListOf<Float>()
    possibleGroupsItems.entries.forEach {
        totals.add(totalOfOneGroup(it.value))
    }
    return totals.minOrNull() ?: -1f
}

/**
 * Подсчет стоимости корзины с учетом скидок указанных в [discount]
 */
fun totalOfOneGroup(items: List<Int>): Float {
    val groupingCart = mutableListOf<Int>()
    var sizeCart = items.countNotEmpty()
    var copyItems = items.toList()
    var total = 0f

    if (sizeCart > 0) {
        do {
            groupingCart.add(sizeCart)
            copyItems = copyItems.slice()
            sizeCart = copyItems.countNotEmpty()
        } while (sizeCart > 0)
    }

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
fun List<Int>.slice() = this.map { it - 1 }

/**
 * Подсчет количества товаров в корзине
 */
fun List<Int>.countNotEmpty() = this.filter { it > 0 }.size

/**
 * Поиск минимального и удаление его из коллекции
 */
fun MutableList<Int>.minAndRemove(): Int? {
    val minimum = this.minOrNull()
    this.remove(minimum)
    return minimum
}