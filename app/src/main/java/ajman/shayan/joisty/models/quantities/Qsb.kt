package ajman.shayan.joisty.models.quantities

import ajman.shayan.joisty.enums.Unit
import ajman.shayan.joisty.models.Quantity
import ajman.shayan.joisty.models.templates.Assignment

class Qsb: Quantity() {
    override val name = "Qsb"
    override val dependencies = mutableSetOf("Asb", "d")

    override fun evaluate(dataSet: MutableMap<String, Double>): Pair<Double, MutableList<Assignment>> {
        val Asb = dataSet["Asb"] ?: 0.0
        val d = dataSet["d"] ?: 0.0
        val assignments = mutableListOf<Assignment>()
        val Qsb = Asb * d
        assignments.add(Assignment("Q_{sb}", Qsb, Unit.CM3, "A_{sb} d"))
        return Pair(Qsb, assignments)
    }
}