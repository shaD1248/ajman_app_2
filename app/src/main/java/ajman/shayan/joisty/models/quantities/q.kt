package ajman.shayan.joisty.models.quantities

import ajman.shayan.joisty.enums.Unit
import ajman.shayan.joisty.models.quantity_models.EvaluatableQuantity
import ajman.shayan.joisty.models.datasets.DataSet
import ajman.shayan.joisty.models.templates.Assignment

class q(dataSet: DataSet): EvaluatableQuantity(dataSet) {
    override val name = "q"
    override val dependencies = mutableSetOf("w", "b")

    override fun evaluate(): Triple<Double, MutableList<Assignment>, MutableSet<String>> {
        actualDependencies = mutableSetOf(dataSet.w, dataSet.b)
        val q: Double = dataSet.w * dataSet.b
        val assignments = mutableListOf(
            Assignment("q", q, Unit.KGF_OVER_M, "w b")
        )
        value = q
        this.assignments = assignments
        return Triple(q, assignments, mutableSetOf())
    }
}