package ajman.shayan.joisty.models.quantities

import ajman.shayan.joisty.enums.Unit
import ajman.shayan.joisty.models.Quantity
import ajman.shayan.joisty.models.templates.Assignment

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