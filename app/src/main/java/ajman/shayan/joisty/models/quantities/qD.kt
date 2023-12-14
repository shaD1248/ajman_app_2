package ajman.shayan.joisty.models.quantities

import ajman.shayan.joisty.enums.Unit
import ajman.shayan.joisty.models.quantity_models.EvaluatableQuantity
import ajman.shayan.joisty.models.datasets.DataSet
import ajman.shayan.joisty.models.templates.Assignment

class qD(dataSet: DataSet): EvaluatableQuantity(dataSet) {
    override val name = "qD"
    override val dependencies = mutableSetOf("wD", "b")

    override fun evaluate(): Triple<Double, MutableList<Assignment>, MutableSet<String>> {
        actualDependencies = mutableSetOf(dataSet.wD, dataSet.b)
        val qD: Double = dataSet.wD * dataSet.b
        val assignments = mutableListOf(
            Assignment("q_D", qD, Unit.KGF_OVER_M, "w_D b")
        )
        value = qD
        this.assignments = assignments
        return Triple(qD, assignments, mutableSetOf())
    }
}