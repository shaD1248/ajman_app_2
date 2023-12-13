package ajman.shayan.joisty.models.quantities

import ajman.shayan.joisty.enums.Unit
import ajman.shayan.joisty.models.quantity_models.EvaluatableQuantity
import ajman.shayan.joisty.models.datasets.DataSet
import ajman.shayan.joisty.models.quantity_models.div
import ajman.shayan.joisty.models.quantity_models.max
import ajman.shayan.joisty.models.templates.Assignment
import ajman.shayan.joisty.models.quantity_models.times
import kotlin.math.pow

class Vu(dataSet: DataSet): EvaluatableQuantity(dataSet) {
    override val name = "Vu"
    override val dependencies = mutableSetOf("qu", "xv", "L")

    override fun evaluate(): Triple<Double, MutableList<Assignment>, MutableSet<String>> {
        actualDependencies = mutableSetOf(dataSet.qu, dataSet.xv, dataSet.L)
        val Vu: Double = 0.5 * dataSet.qu / dataSet.L * max(dataSet.xv, dataSet.L - dataSet.xv).pow(2.0)
        val assignments = mutableListOf(
            Assignment("V_u", Vu, Unit.TF, "\\frac{q_u}{2 L} \\left(\\max\\left(x_v, L - x_v\\right)\\right)^2")
        )
        value = Vu
        this.assignments = assignments
        return Triple(Vu, assignments, mutableSetOf())
    }
}