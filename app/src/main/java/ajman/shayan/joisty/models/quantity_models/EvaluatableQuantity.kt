package ajman.shayan.joisty.models.quantity_models

import ajman.shayan.joisty.models.datasets.DataSet
import ajman.shayan.joisty.models.templates.Assignment

abstract class EvaluatableQuantity(val dataSet: DataSet) : NamedQuantity() {
    abstract val dependencies: MutableSet<String>
    abstract fun evaluate(): Triple<Double, MutableList<Assignment>, MutableSet<String>>
}