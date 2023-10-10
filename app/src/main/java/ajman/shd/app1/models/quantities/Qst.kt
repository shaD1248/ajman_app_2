package ajman.shd.app1.models.quantities

import ajman.shd.app1.enums.Unit
import ajman.shd.app1.models.Quantity
import ajman.shd.app1.models.templates.Assignment

class Qst: Quantity() {
    override val name = "Qst"
    override val dependencies = mutableSetOf("Ast", "d", "dj")

    override fun evaluate(dataSet: MutableMap<String, Double>): Pair<Double, MutableList<Assignment>> {
        val Ast = dataSet["Ast"] ?: 0.0
        val d = dataSet["d"] ?: 0.0
        val dj = dataSet["dj"] ?: 0.0
        val assignments = mutableListOf<Assignment>()
        val Qst = Ast * (d - dj)
        assignments.add(Assignment("Q_{st}", Qst, Unit.CM3, "A_{st} \\left(d - d_j\\right)"))
        return Pair(Qst, assignments)
    }
}