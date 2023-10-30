package ajman.shayan.joisty.models.quantities

import ajman.shayan.joisty.enums.Unit
import ajman.shayan.joisty.models.Quantity
import ajman.shayan.joisty.models.templates.Assignment

class Mp: Quantity() {
    override val name = "Mp"
    override val dependencies = mutableSetOf("Asb", "Fy", "d")

    override fun evaluate(dataSet: MutableMap<String, Double>): Pair<Double, MutableList<Assignment>> {
        val Asb = dataSet["Asb"] ?: 0.0
        val Fy = dataSet["Fy"] ?: 0.0
        val d = dataSet["d"] ?: 0.0
        val assignments = mutableListOf<Assignment>()
        val Mp = Asb * Fy * d
        assignments.add(Assignment("M_p", Mp, Unit.KGFM, "A_{sb} F_y d"))
        return Pair(Mp, assignments)
    }
}