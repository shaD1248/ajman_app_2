package ajman.shayan.joisty.models.quantities

import ajman.shayan.joisty.enums.Unit
import ajman.shayan.joisty.models.quantity_models.EvaluatableQuantity
import ajman.shayan.joisty.models.datasets.DataSet
import ajman.shayan.joisty.models.templates.Assignment

class Sb(dataSet: DataSet): EvaluatableQuantity(dataSet) {
    override val name = "Sb"
    override val dependencies = mutableSetOf("I_composite", "d", "y_composite")

    override fun evaluate(): Triple<Double, MutableList<Assignment>, MutableSet<String>> {
        val assignments = mutableListOf<Assignment>()
        actualDependencies = mutableSetOf(dataSet.I_composite, dataSet.d, dataSet.y_composite)
        val Sb = dataSet.I_composite / (dataSet.d - dataSet.y_composite)
        assignments.add(Assignment("S_b", Sb, Unit.CM3, "\\frac{I_{composite}}{d - y_{composite}}"))
        value = Sb
        this.assignments = assignments
        return Triple(Sb, assignments, mutableSetOf())
    }
}