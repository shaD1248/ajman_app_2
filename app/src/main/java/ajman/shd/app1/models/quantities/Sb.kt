package ajman.shd.app1.models.quantities

import ajman.shd.app1.enums.Unit
import ajman.shd.app1.models.Quantity
import ajman.shd.app1.models.templates.Assignment

class Sb: Quantity() {
    override val name = "Sb"
    override val dependencies = mutableSetOf("I_composite", "d", "y_composite")

    override fun evaluate(dataSet: MutableMap<String, Double>): Pair<Double, MutableList<Assignment>> {
        val I_composite = dataSet["I_composite"] ?: 0.0
        val d = dataSet["d"] ?: 0.0
        val y_composite = dataSet["y_composite"] ?: 0.0
        val assignments = mutableListOf<Assignment>()
        val Sb = I_composite / (d - y_composite)
        assignments.add(Assignment("S_b", Sb, Unit.CM3, "\\frac{I_{composite}}{d - y_{composite}}"))
        return Pair(Sb, assignments)
    }
}