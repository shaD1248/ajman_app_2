package ajman.shayan.joisty.models.quantities

import ajman.shayan.joisty.enums.Unit
import ajman.shayan.joisty.models.quantity_models.EvaluatableQuantity
import ajman.shayan.joisty.models.datasets.DataSet
import ajman.shayan.joisty.models.templates.Assignment
import ajman.shayan.joisty.models.quantity_models.times

class Mu(dataSet: DataSet): EvaluatableQuantity(dataSet) {
    override val name = "Mu"
    override val dependencies = mutableSetOf("qu", "xb", "L")

    override fun evaluate(): Triple<Double, MutableList<Assignment>, MutableSet<String>> {
        actualDependencies = mutableSetOf(dataSet.qu, dataSet.xb, dataSet.L)
        val Mu: Double = 0.5 * dataSet.qu * dataSet.xb * (dataSet.L - dataSet.xb)
        val assignments = mutableListOf(
            Assignment("M_u", Mu, Unit.TFM, "\\frac{1}{2} q_u x_b \\left(L - x_b\\right)")
        )
        value = Mu
        this.assignments = assignments
        return Triple(Mu, assignments, mutableSetOf())
    }
}