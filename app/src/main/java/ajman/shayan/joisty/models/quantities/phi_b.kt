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
        var formula: String? = null
        val phi_b = if (!dataSet.hasConcreteWeb) {
            0.9
        } else {
            actualDependencies.add(dataSet.epsilon_t)
            if (dataSet.epsilon_t >= 0.005) {
                0.9
            } else {
                actualDependencies += mutableSetOf(dataSet.Fy, dataSet.Es)
                if (dataSet.epsilon_t > dataSet.Fy / dataSet.Es) {
                    formula = "0.9 - (0.9 - 0.65) * (0.005 - epsilon_t) / (0.005 - F_y / E_s)"
                    0.9 - (0.9 - 0.65) * (0.005 - dataSet.epsilon_t) / (0.005 - dataSet.Fy / dataSet.Es)
                } else {
                    0.65
                }
            }
        }
        val assignments = mutableListOf(
            Assignment("\\phi_b", phi_b, Unit.UNIT, formula)
        )
        value = phi_b
        this.assignments = assignments
        return Triple(phi_b, assignments, mutableSetOf())
    }
}