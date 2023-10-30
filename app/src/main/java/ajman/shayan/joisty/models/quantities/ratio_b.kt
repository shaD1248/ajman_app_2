package ajman.shayan.joisty.models.quantities

import ajman.shayan.joisty.enums.Unit
import ajman.shayan.joisty.models.Quantity
import ajman.shayan.joisty.models.templates.Assignment

class ratio_b: Quantity() {
    override val name = "ratio_b"
    override val dependencies = mutableSetOf("Mu", "phi_b", "Mn")

    override fun evaluate(dataSet: MutableMap<String, Double>): Pair<Double, MutableList<Assignment>> {
        val Mu = dataSet["Mu"] ?: 0.0
        val phi_b = dataSet["phi_b"] ?: 0.0
        val Mn = dataSet["Mn"] ?: 0.0
        val ratio_b: Double = Mu / phi_b / Mn
        val assignments = mutableListOf(
            Assignment("ratio_b", ratio_b, Unit.UNIT, "\\frac{M_u}{\\phi_b M_n}")
        )
        return Pair(ratio_b, assignments)
    }
}