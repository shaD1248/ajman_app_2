package ajman.shayan.joisty.models.quantities

import ajman.shayan.joisty.enums.Unit
import ajman.shayan.joisty.models.quantity_models.EvaluatableQuantity
import ajman.shayan.joisty.models.datasets.DataSet
import ajman.shayan.joisty.models.quantity_models.div
import ajman.shayan.joisty.models.quantity_models.times
import ajman.shayan.joisty.models.templates.Assignment

class epsilon_t(dataSet: DataSet): EvaluatableQuantity(dataSet) {
    override val name = "epsilon_t"
    override val dependencies = mutableSetOf("d", "a")

    override fun evaluate(): Triple<Double, MutableList<Assignment>, MutableSet<String>> {
        actualDependencies = mutableSetOf(dataSet.d, dataSet.a)
        val epsilon_t: Double = 0.003 * (0.85 * dataSet.d / dataSet.a - 1)
        val assignments = mutableListOf(
            Assignment("\\epsilon_t", epsilon_t, Unit.UNIT, "0.003\\left(0.85\\frac{d}{a} - 1\\right)")
        )
        value = epsilon_t
        this.assignments = assignments
        return Triple(epsilon_t, assignments, mutableSetOf())
    }
}