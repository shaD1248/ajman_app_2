package ajman.shayan.joisty.models.quantities

import ajman.shayan.joisty.enums.Unit
import ajman.shayan.joisty.models.quantity_models.EvaluatableQuantity
import ajman.shayan.joisty.models.datasets.DataSet
import ajman.shayan.joisty.models.quantity_models.div
import ajman.shayan.joisty.models.templates.Assignment

class ratio_b(dataSet: DataSet): EvaluatableQuantity(dataSet) {
    override val name = "ratio_b"
    override val dependencies = mutableSetOf("Mu", "phi_b", "Mn")

    override fun evaluate(): Triple<Double, MutableList<Assignment>, MutableSet<String>> {
        actualDependencies = mutableSetOf(dataSet.Mu, dataSet.phi_b, dataSet.Mn)
        val ratio_b: Double = dataSet.Mu / dataSet.phi_b / dataSet.Mn
        val assignments = mutableListOf(
            Assignment("ratio_b", ratio_b, Unit.UNIT, "\\frac{M_u}{\\phi_b M_n}")
        )
        value = ratio_b
        this.assignments = assignments
        return Triple(ratio_b, assignments, mutableSetOf())
    }
}