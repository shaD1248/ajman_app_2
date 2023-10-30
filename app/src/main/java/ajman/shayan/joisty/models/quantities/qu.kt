package ajman.shayan.joisty.models.quantities

import ajman.shayan.joisty.enums.Unit
import ajman.shayan.joisty.models.Quantity
import ajman.shayan.joisty.models.templates.Assignment

class qu: Quantity() {
    override val name = "qu"
    override val dependencies = mutableSetOf("wu", "b")

    override fun evaluate(dataSet: MutableMap<String, Double>): Pair<Double, MutableList<Assignment>> {
        val wu = dataSet["wu"] ?: 0.0
        val b = dataSet["b"] ?: 0.0
        val qu: Double = wu * b
        val assignments = mutableListOf(
            Assignment("q_u", qu, Unit.KGF_OVER_M, "w_u b")
        )
        return Pair(qu, assignments)
    }
}