package ajman.shayan.joisty.models.quantities

import ajman.shayan.joisty.enums.Unit
import ajman.shayan.joisty.models.Quantity
import ajman.shayan.joisty.models.templates.Assignment

class epsilon_t: Quantity() {
    override val name = "epsilon_t"
    override val dependencies = mutableSetOf("d", "a")

    override fun evaluate(dataSet: MutableMap<String, Double>): Pair<Double, MutableList<Assignment>> {
        val d = dataSet["d"] ?: 0.0
        val a = dataSet["a"] ?: 0.0
        val epsilon_t: Double = 0.003 * (0.85 * d / a - 1)
        val assignments = mutableListOf(
            Assignment("\\epsilon_t", epsilon_t, Unit.UNIT, "0.003\\left(0.85\\frac{d}{a} - 1\\right)")
        )
        return Pair(epsilon_t, assignments)
    }
}