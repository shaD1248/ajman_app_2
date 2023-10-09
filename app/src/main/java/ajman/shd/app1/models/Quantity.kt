package ajman.shd.app1.models

import ajman.shd.app1.models.templates.Assignment

abstract class Quantity {
    abstract val name: String
    abstract val dependencies: MutableSet<String>
    abstract fun evaluate(dataSet: MutableMap<String, Double>): Pair<Double, MutableList<Assignment>>
}