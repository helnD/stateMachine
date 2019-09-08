package data

import functional.codeCreator
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import javafx.beans.value.ObservableValue
import tornadofx.observable
import tornadofx.property
import java.util.*
import kotlin.properties.ObservableProperty

class Relation (val startState: Int,
                val endState: Int,
                input: Int,
                output: Int) {

    val input = SimpleIntegerProperty(input)
    val output = SimpleIntegerProperty(output)

    fun toRow(maxInputCode: Int, maxStateCode: Int,
              maxOutputCode: Int): List<Int> {

        val result = mutableListOf<Int>()

        result.addAll(codeCreator(input.value, maxInputCode))
        result.addAll(codeCreator(startState, maxStateCode))
        result.addAll(codeCreator(endState, maxStateCode))
        result.addAll(codeCreator(output.value, maxOutputCode))

        return result
    }

}