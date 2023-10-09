package ajman.shd.app1.models.quantities

import ajman.shd.app1.enums.Unit
import ajman.shd.app1.models.Quantity
import ajman.shd.app1.models.templates.Assignment

class epsilon_t: Quantity() {
    override val name = "epsilon_t"
    override val dependencies = mutableSetOf("d", "a")

    override fun evaluate(dataSet: MutableMap<String, Double>): Pair<Double, MutableList<Assignment>> {
        val d = dataSet["d"] ?: 0.0
        val a = dataSet["a"] ?: 0.0
        val epsilon_t: Double = 0.003 * (0.85 * d / a - 1)
        val assignments = mutableListOf(
            Assignment("epsilon_t", epsilon_t, Unit.UNIT, "0.003 * (0.85 * d / a - 1)")
        )
        return Pair(epsilon_t, assignments)
    }
}