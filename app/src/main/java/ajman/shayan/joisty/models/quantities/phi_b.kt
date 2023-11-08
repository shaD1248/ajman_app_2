package ajman.shayan.joisty.models.quantities

import ajman.shayan.joisty.enums.Unit
import ajman.shayan.joisty.models.quantity_models.EvaluatableQuantity
import ajman.shayan.joisty.models.datasets.DataSet
import ajman.shayan.joisty.models.quantity_models.minus
import ajman.shayan.joisty.models.templates.Assignment

class phi_b(dataSet: DataSet): EvaluatableQuantity(dataSet) {
    override val name = "phi_b"
    override val dependencies = mutableSetOf("hasConcreteWeb", "epsilon_t", "Fy", "Es")

    override fun evaluate(): Triple<Double, MutableList<Assignment>, MutableSet<String>> {
        val hasConcreteWeb = dataSet.hasConcreteWeb
        val epsilon_t = dataSet.epsilon_t
        val Fy = dataSet.Fy
        val Es = dataSet.Es
        var formula: String? = null
        val phi_b = if (!hasConcreteWeb || epsilon_t >= 0.005) {
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
        return Triple(phi_b, assignments, mutableSetOf())
    }
}