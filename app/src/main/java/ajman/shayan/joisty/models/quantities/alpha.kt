package ajman.shayan.joisty.models.quantities

import ajman.shayan.joisty.enums.Unit
import ajman.shayan.joisty.models.quantity_models.EvaluatableQuantity
import ajman.shayan.joisty.models.datasets.DataSet
import ajman.shayan.joisty.models.quantity_models.div
import ajman.shayan.joisty.models.templates.Assignment
import ajman.shayan.joisty.models.quantity_models.times
import kotlin.math.atan

class alpha(dataSet: DataSet): EvaluatableQuantity(dataSet) {
    override val name = "alpha"
    override val dependencies = mutableSetOf("dj", "s")

    override fun evaluate(): Triple<Double, MutableList<Assignment>, MutableSet<String>> {
        actualDependencies = mutableSetOf(dataSet.dj, dataSet.s)
        val alpha: Double = atan(2 * dataSet.dj / dataSet.s)
        this.assignments = mutableListOf(
            Assignment("\\alpha", alpha, Unit.TF, "\\tan^{-1}{\\frac{2 d_j}{s}}")
        )
        value = alpha
        return Triple(alpha, assignments, mutableSetOf())
    }
}