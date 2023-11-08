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
        val Mu = dataSet.Mu
        val phi_b = dataSet.phi_b
        val Mn = dataSet.Mn
        val ratio_b: Double = Mu / phi_b / Mn
        val assignments = mutableListOf(
            Assignment("ratio_b", ratio_b, Unit.UNIT, "\\frac{M_u}{\\phi_b M_n}")
        )
        return Triple(ratio_b, assignments, mutableSetOf())
    }
}