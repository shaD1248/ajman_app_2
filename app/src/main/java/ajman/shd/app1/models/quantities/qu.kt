package ajman.shd.app1.models.quantities

import ajman.shd.app1.enums.Unit
import ajman.shd.app1.models.Quantity
import ajman.shd.app1.models.templates.Assignment

class qu: Quantity() {
    override val name = "qu"
    override val dependencies = mutableSetOf("wu", "b")

    override fun evaluate(dataSet: MutableMap<String, Double>): Pair<Double, MutableList<Assignment>> {
        val wu = dataSet["wu"] ?: 0.0
        val b = dataSet["b"] ?: 0.0
        val qu: Double = wu * b
        val assignments = mutableListOf(
            Assignment("qu", qu, Unit.KGF_OVER_M, "wu * b")
        )
        return Pair(qu, assignments)
    }
}