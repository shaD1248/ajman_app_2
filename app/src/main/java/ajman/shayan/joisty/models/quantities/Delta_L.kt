package ajman.shayan.joisty.models.quantities

import ajman.shayan.joisty.enums.Unit
import ajman.shayan.joisty.models.quantity_models.EvaluatableQuantity
import ajman.shayan.joisty.models.datasets.DataSet
import ajman.shayan.joisty.models.quantity_models.div
import ajman.shayan.joisty.models.templates.Assignment

class Delta_L(dataSet: DataSet): EvaluatableQuantity(dataSet) {
    override val name = "Delta_L"
    override val dependencies = mutableSetOf("qL")

    override fun evaluate(): Triple<Double, MutableList<Assignment>, MutableSet<String>> {
        actualDependencies = mutableSetOf(dataSet.qL, dataSet.L, dataSet.Es, dataSet.I_composite)
        val Delta_L: Double = 5.0 / 384 * (dataSet.qL * dataSet.L.pow(4) / dataSet.Es / dataSet.I_composite)
        val assignments = mutableListOf(
            Assignment("\\Delta_L", Delta_L, Unit.CM, "\\frac{5 q_L L^4}{384 E_s I_{composite}}")
        )
        value = Delta_L
        this.assignments = assignments
        return Triple(Delta_L, assignments, mutableSetOf())
    }
}