package ajman.shayan.joisty.models.quantities

import ajman.shayan.joisty.enums.Unit
import ajman.shayan.joisty.models.quantity_models.EvaluatableQuantity
import ajman.shayan.joisty.models.datasets.DataSet
import ajman.shayan.joisty.models.templates.Assignment

class Delta_aL(dataSet: DataSet): EvaluatableQuantity(dataSet) {
    override val name = "Delta_aL"
    override val dependencies = mutableSetOf("L")

    override fun evaluate(): Triple<Double, MutableList<Assignment>, MutableSet<String>> {
        actualDependencies = mutableSetOf(dataSet.L)
        val Delta_aL: Double = dataSet.L / 360
        val assignments = mutableListOf(
            Assignment("\\Delta_{aL}", Delta_aL, Unit.CM)
        )
        value = Delta_aL
        this.assignments = assignments
        return Triple(Delta_aL, assignments, mutableSetOf())
    }
}