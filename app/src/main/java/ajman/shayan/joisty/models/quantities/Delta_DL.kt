package ajman.shayan.joisty.models.quantities

import ajman.shayan.joisty.enums.Unit
import ajman.shayan.joisty.models.quantity_models.EvaluatableQuantity
import ajman.shayan.joisty.models.datasets.DataSet
import ajman.shayan.joisty.models.quantity_models.div
import ajman.shayan.joisty.models.templates.Assignment

class Delta_DL(dataSet: DataSet): EvaluatableQuantity(dataSet) {
    override val name = "Delta_DL"
    override val dependencies = mutableSetOf("q", "L", "Es", "I_composite")

    override fun evaluate(): Triple<Double, MutableList<Assignment>, MutableSet<String>> {
        actualDependencies = mutableSetOf(dataSet.q, dataSet.L, dataSet.Es, dataSet.I_composite)
        val Delta_DL: Double = 5.0 / 384 * (dataSet.q * dataSet.L.pow(4) / dataSet.Es / dataSet.I_composite)
        val assignments = mutableListOf(
            Assignment("\\Delta_{D\\&L}", Delta_DL, Unit.CM, "\\frac{5 q L^4}{384 E_s I_{composite}}")
        )
        value = Delta_DL
        this.assignments = assignments
        return Triple(Delta_DL, assignments, mutableSetOf())
    }
}