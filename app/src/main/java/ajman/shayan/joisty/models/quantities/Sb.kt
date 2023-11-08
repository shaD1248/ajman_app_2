package ajman.shayan.joisty.models.quantities

import ajman.shayan.joisty.enums.Unit
import ajman.shayan.joisty.models.quantity_models.EvaluatableQuantity
import ajman.shayan.joisty.models.datasets.DataSet
import ajman.shayan.joisty.models.quantity_models.minus
import ajman.shayan.joisty.models.templates.Assignment

class Sb(dataSet: DataSet): EvaluatableQuantity(dataSet) {
    override val name = "Sb"
    override val dependencies = mutableSetOf("I_composite", "d", "y_composite")

    override fun evaluate(): Triple<Double, MutableList<Assignment>, MutableSet<String>> {
        val I_composite = dataSet.I_composite
        val d = dataSet.d
        val y_composite = dataSet.y_composite
        val assignments = mutableListOf<Assignment>()
        val Sb = I_composite / (d - y_composite)
        assignments.add(Assignment("S_b", Sb, Unit.CM3, "\\frac{I_{composite}}{d - y_{composite}}"))
        return Triple(Sb, assignments, mutableSetOf())
    }
}