package ajman.shd.app1.models.quantities

import ajman.shd.app1.enums.Unit
import ajman.shd.app1.models.Quantity
import ajman.shd.app1.models.templates.Assignment

class Sc: Quantity() {
    override val name = "Sc"
    override val dependencies = mutableSetOf("ns", "I_composite", "y_composite")

    override fun evaluate(dataSet: MutableMap<String, Double>): Pair<Double, MutableList<Assignment>> {
        val ns = dataSet["ns"] ?: 0.0
        val I_composite = dataSet["I_composite"] ?: 0.0
        val y_composite = dataSet["y_composite"] ?: 0.0
        val assignments = mutableListOf<Assignment>()
        val Sc = -ns * I_composite / y_composite
        assignments.add(Assignment("S_c", Sc, Unit.CM3, "-\\frac{n_s I_{composite}}{y_{composite}}"))
        return Pair(Sc, assignments)
    }
}