package ajman.shayan.joisty.models.quantities

import ajman.shayan.joisty.enums.Unit
import ajman.shayan.joisty.models.quantity_models.EvaluatableQuantity
import ajman.shayan.joisty.models.datasets.DataSet
import ajman.shayan.joisty.models.templates.Assignment
import ajman.shayan.joisty.models.quantity_models.times

class Mu(dataSet: DataSet): EvaluatableQuantity(dataSet) {
    override val name = "Mu"
    override val dependencies = mutableSetOf("qu", "x", "L")

    override fun evaluate(): Triple<Double, MutableList<Assignment>, MutableSet<String>> {
        actualDependencies = mutableSetOf(dataSet.qu, dataSet.x, dataSet.L)
        val Mu: Double = 0.5 * dataSet.qu * dataSet.x * (dataSet.L - dataSet.x)
        val assignments = mutableListOf(
            Assignment("M_u", Mu, Unit.KGFM, "q_u x \\left(L - x\\right)")
        )
        value = Mu
        this.assignments = assignments
        return Triple(Mu, assignments, mutableSetOf())
    }
}