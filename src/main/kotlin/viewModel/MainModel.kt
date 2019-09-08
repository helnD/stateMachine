package viewModel

import data.Relation
import data.Scheme
import functional.maxCodeSize
import tornadofx.ViewModel

class MainModel : ViewModel() {

    val scheme = Scheme(listOf(
        Relation(0, 0, 0, 4),
        Relation(0, 1, 2, 2),
        Relation(0, 2, 3, 1),
        Relation(0, 4, 10, 3),
        Relation(1, 0, 1, 4),
        Relation(1, 1, 4, 4),
        Relation(2, 0, 3, 1),
        Relation(2, 3, 2, 0),
        Relation(3, 0, 4, 0),
        Relation(3, 4, 0, 1),
        Relation(4, 3, 3, 4)
    ))

    fun getTable() : Map<String, List<Int>>{

        val maxInputCode = maxCodeSize(scheme
            .listOfRelation
            .maxBy { it.input.value }!!
            .input.value)

        val maxStartStateCode = maxCodeSize(scheme
            .listOfRelation
            .maxBy { it.startState }!!
            .startState)

        val maxEndStateCode = maxCodeSize(scheme
            .listOfRelation
            .maxBy { it.endState }!!
            .endState)

        val maxStateCode = if (maxStartStateCode > maxEndStateCode) maxStartStateCode
                                else maxEndStateCode

        val maxOutputCode = maxCodeSize(scheme
            .listOfRelation
            .maxBy { it.output.value }!!
            .output.value)

        val result = mutableMapOf<String, MutableList<Int>>()

        for (index in 0 until maxInputCode) {
            result["X$index"] = mutableListOf()
        }

        for (index in 0 until maxStateCode) {
            result["Q$index"] = mutableListOf()
        }

        for (index in 0 until maxStateCode) {
            result["q$index"] = mutableListOf()
        }

        for (index in 0 until maxOutputCode) {
            result["Y$index"] = mutableListOf()
        }

        val rows = scheme.listOfRelation.map {
            it.toRow(maxInputCode, maxStateCode, maxOutputCode)
        }

        rows.forEach { row ->
            for (index in row.indices) {

                val key = when {
                    index < maxInputCode -> {
                        "X$index"
                    }
                    index in maxInputCode..(maxInputCode + maxStateCode) -> {
                        "Q${index - maxInputCode}"
                    }
                    index in (maxInputCode + maxStateCode)..(maxInputCode + maxStateCode * 2) -> {
                        "q${index - maxInputCode + maxStateCode}"
                    }
                    else -> {
                        "Y${index - maxInputCode + maxStateCode * 2}"
                    }
                }

                result[key]!!.add(row[index])
            }
        }

        return result
    }

}