package ajman.shd.app1.models.quantities

import ajman.shd.app1.enums.Unit
import ajman.shd.app1.models.Quantity
import ajman.shd.app1.models.templates.Assignment

class phi_b: Quantity() {
    override val name = "phi_b"
    override val dependencies = mutableSetOf("hasConcreteWeb", "epsilon_t", "Fy", "Es")

    override fun evaluate(dataSet: MutableMap<String, Double>): Pair<Double, MutableList<Assignment>> {
        val hasConcreteWeb = dataSet["hasConcreteWeb"] ?: 1.0
        val epsilon_t = dataSet["epsilon_t"] ?: 0.0
        val Fy = dataSet["Fy"] ?: 0.0
        val Es = dataSet["Es"] ?: 0.0
        var formula: String? = null
        val phi_b = if (hasConcreteWeb == 0.0 || epsilon_t >= 0.005) {
            0.9
        } else if (epsilon_t > Fy / Es) {
            formula = "0.9 - (0.9 - 0.65) * (0.005 - epsilon_t) / (0.005 - F_y / E_s)"
            0.9 - (0.9 - 0.65) * (0.005 - epsilon_t) / (0.005 - Fy / Es)
        } else {
            0.65
        }
        val assignments = mutableListOf(
            Assignment("\\phi_b", phi_b, Unit.UNIT, formula)
        )
        return Pair(phi_b, assignments)
    }
}