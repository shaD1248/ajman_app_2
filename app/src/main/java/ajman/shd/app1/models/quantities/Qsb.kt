package ajman.shd.app1.models.quantities

import ajman.shd.app1.enums.Unit
import ajman.shd.app1.models.Quantity
import ajman.shd.app1.models.templates.Assignment

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