package ajman.shayan.joisty.models.quantities

import ajman.shayan.joisty.enums.Unit
import ajman.shayan.joisty.models.quantity_models.EvaluatableQuantity
import ajman.shayan.joisty.models.datasets.DataSet
import ajman.shayan.joisty.models.quantity_models.max
import ajman.shayan.joisty.models.templates.Assignment
import ajman.shayan.joisty.models.quantity_models.times

class Vu(dataSet: DataSet): EvaluatableQuantity(dataSet) {
    override val name = "Vu"
    override val dependencies = mutableSetOf("qu", "x", "L")

    override fun evaluate(): Triple<Double, MutableList<Assignment>, MutableSet<String>> {
        actualDependencies = mutableSetOf(dataSet.qu, dataSet.x, dataSet.L)
        val Vu: Double = 0.5 * dataSet.qu * max(dataSet.x, dataSet.L - dataSet.x)
        val assignments = mutableListOf(
            Assignment("V_u", Vu, Unit.TF, "\\frac{1}{2} q_u \\max\\left(x, L - x\\right)")
        )
        value = Vu
        this.assignments = assignments
        return Triple(Vu, assignments, mutableSetOf())
    }
}