package ajman.shayan.joisty.models.quantities

import ajman.shayan.joisty.enums.Unit
import ajman.shayan.joisty.models.quantity_models.EvaluatableQuantity
import ajman.shayan.joisty.models.datasets.DataSet
import ajman.shayan.joisty.models.templates.Assignment

class qL(dataSet: DataSet): EvaluatableQuantity(dataSet) {
    override val name = "qL"
    override val dependencies = mutableSetOf("wL", "b")

    override fun evaluate(): Triple<Double, MutableList<Assignment>, MutableSet<String>> {
        actualDependencies = mutableSetOf(dataSet.wL, dataSet.b)
        val qL: Double = dataSet.wL * dataSet.b
        val assignments = mutableListOf(
            Assignment("q_L", qL, Unit.KGF_OVER_M, "w_L b")
        )
        value = qL
        this.assignments = assignments
        return Triple(qL, assignments, mutableSetOf())
    }
}