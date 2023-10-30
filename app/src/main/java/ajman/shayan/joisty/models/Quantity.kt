package ajman.shayan.joisty.models

import ajman.shayan.joisty.models.templates.Assignment

abstract class Quantity {
    abstract val name: String
    abstract val dependencies: MutableSet<String>
    abstract fun evaluate(dataSet: MutableMap<String, Double>): Pair<Double, MutableList<Assignment>>
}