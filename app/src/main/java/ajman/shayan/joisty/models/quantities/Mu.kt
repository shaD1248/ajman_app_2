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
        val qu = dataSet.qu
        val x = dataSet.x
        val L = dataSet.L
        val Mu: Double = 0.5 * qu * x * (L - x)
        val assignments = mutableListOf(
            Assignment("M_u", Mu, Unit.KGFM, "q_u x \\left(L - x\\right)")
        )
        return Triple(Mu, assignments, mutableSetOf())
    }
}