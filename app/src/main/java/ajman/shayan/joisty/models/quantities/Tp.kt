package ajman.shayan.joisty.models.quantities

import ajman.shayan.joisty.enums.Unit
import ajman.shayan.joisty.models.Quantity
import ajman.shayan.joisty.models.templates.Assignment

class Tp: Quantity() {
    override val name = "Tp"
    override val dependencies = mutableSetOf("Asb", "Fy")

    override fun evaluate(dataSet: MutableMap<String, Double>): Pair<Double, MutableList<Assignment>> {
        val Asb = dataSet["Asb"] ?: 0.0
        val Fy = dataSet["Fy"] ?: 0.0
        val assignments = mutableListOf<Assignment>()
        val Tp = Asb * Fy
        assignments.add(Assignment("T_p", Tp, Unit.TF, "A_{sb} F_y"))
        return Pair(Tp, assignments)
    }
}