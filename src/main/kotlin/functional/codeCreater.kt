package functional

fun codeCreator(index: Int, maxIndex: Int): List<Int> {

    var startIndex = 1
    val result = mutableListOf<Int>()

    while (startIndex <= maxIndex) {
        result.add(index and startIndex)
        startIndex = startIndex shl 1
    }

    return result.map { if (it > 0) 1 else 0 }
        .reversed()
}