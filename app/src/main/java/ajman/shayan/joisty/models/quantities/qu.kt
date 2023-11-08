package ajman.shayan.joisty.models.quantities

import ajman.shayan.joisty.enums.Unit
import ajman.shayan.joisty.models.quantity_models.EvaluatableQuantity
import ajman.shayan.joisty.models.datasets.DataSet
import ajman.shayan.joisty.models.templates.Assignment

class qu(dataSet: DataSet): EvaluatableQuantity(dataSet) {
    override val name = "qu"
    override val dependencies = mutableSetOf("wu", "b")

    override fun evaluate(): Triple<Double, MutableList<Assignment>, MutableSet<String>> {
        val wu = dataSet.wu
        val b = dataSet.b
        val qu: Double = wu * b
        val assignments = mutableListOf(
            Assignment("q_u", qu, Unit.KGF_OVER_M, "w_u b")
        )
        return Triple(qu, assignments, mutableSetOf())
    }
}