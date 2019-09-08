package functional

import java.lang.Math.pow

fun maxCodeSize(code: Int) : Int {

    if (code == 0) return 1

    var codeSize = 1
    var number = 1

    while (number < code) {
        codeSize++
        number *= 2
    }

    return codeSize
}