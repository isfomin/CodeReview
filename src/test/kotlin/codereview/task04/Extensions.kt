package codereview.task04

fun List<String>.asPair() = this.asPair { a, b ->
    Pair(a, b)
}

fun List<String>.asPair(creator: (val1: String, val2: String) -> Pair<String, String>) = creator(get(0), get(1))