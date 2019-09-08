package views

import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Pos
import javafx.scene.control.TableColumn
import javafx.scene.layout.Border
import javafx.scene.paint.Paint
import javafx.scene.text.Text
import tornadofx.*
import viewModel.MainModel
import java.awt.Color
import javax.swing.text.TableView

class MainView : View() {

    private val model: MainModel by inject()

    private val signals = mutableListOf<Text>()

    val coordinates = listOf(
        -40.0 to 90.0,
        220.0 to 70.0,
        90.0 to 220.0,
        170.0 to 210.0,
        220.0 to 130.0,
        390.0 to 40.0,
        5.0 to 220.0,
        220.0 to 440.0,
        240.0 to 0.0,
        280.0 to 360.0,
        280.0 to 300.0)


    override val root = borderpane {

        center = scrollpane {
            group {

                imageview("graph.png")

                val relations = model.scheme.listOfRelation

                for (index in model.scheme.listOfRelation.indices) {

                    val newText = text {
                        text = "x${relations[index].input.value} / y${relations[index].output.value}"

                        style {
                            x = coordinates[index].first
                            y = coordinates[index].second
                        }
                    }

                    signals.add(newText)
                }

            }
        }

        val table = tableview<String> {

            style {
                maxHeight = 150.px
            }
        }

        //table.columns.add(TableColumn<String, String>("sdf"))

        bottom = table

        left = scrollpane {
            form {
                fieldset {
                    val relations = model.scheme.listOfRelation
                    for (relation in relations) {
                        field ("S${relation.startState} -> S${relation.endState}") {

                            style {
                                spacing = 5.px
                                alignment = Pos.CENTER
                            }

                            label("x") {
                                style {
                                    fontSize = 15.px
                                }
                            }

                            textfield(relation.input) {
                                style {
                                    maxWidth = 30.px
                                }
                                textProperty().addListener {_, _, _ ->
                                    signals[relations.indexOf(relation)].text =
                                        "x${relation.input.value} / y${relation.output.value}"
                                }
                            }

                            label("y") {
                                style {
                                    fontSize = 15.px
                                }
                            }

                            textfield (relation.output) {

                                style {
                                    maxWidth = 30.px
                                }
                                textProperty().addListener {_, _, _ ->
                                    signals[relations.indexOf(relation)].text =
                                        "x${relation.input.value} / y${relation.output.value}"
                                }
                            }
                        }
                    }
                    button ("Создать") {
                        action {
                            val table = model.getTable()
                        }
                    }
                }
            }
        }

    }

}