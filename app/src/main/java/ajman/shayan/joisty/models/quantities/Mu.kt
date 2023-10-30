package ajman.shayan.joisty.models.quantities

import ajman.shayan.joisty.enums.Unit
import ajman.shayan.joisty.models.Quantity
import ajman.shayan.joisty.models.templates.Assignment

class Mu: Quantity() {
    override val name = "Mu"
    override val dependencies = mutableSetOf("qu", "x", "L")

    override fun evaluate(dataSet: MutableMap<String, Double>): Pair<Double, MutableList<Assignment>> {
        val qu = dataSet["qu"] ?: 0.0
        val x = dataSet["x"] ?: 0.0
        val L = dataSet["L"] ?: 0.0
        val Mu: Double = 0.5 * qu * x * (L - x)
        val assignments = mutableListOf(
            Assignment("M_u", Mu, Unit.KGFM, "q_u x \\left(L - x\\right)")
        )
        return Pair(Mu, assignments)
    }
}