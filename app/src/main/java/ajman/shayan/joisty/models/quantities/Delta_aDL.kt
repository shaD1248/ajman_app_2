package ajman.shayan.joisty.models.quantities

import ajman.shayan.joisty.enums.Unit
import ajman.shayan.joisty.models.quantity_models.EvaluatableQuantity
import ajman.shayan.joisty.models.datasets.DataSet
import ajman.shayan.joisty.models.templates.Assignment

class Delta_aDL(dataSet: DataSet): EvaluatableQuantity(dataSet) {
    override val name = "Delta_aDL"
    override val dependencies = mutableSetOf("L")

    override fun evaluate(): Triple<Double, MutableList<Assignment>, MutableSet<String>> {
        actualDependencies = mutableSetOf(dataSet.L)
        val Delta_aDL: Double = dataSet.L / 240
        val assignments = mutableListOf(
            Assignment("\\Delta_{aD\\&L}", Delta_aDL, Unit.CM)
        )
        value = Delta_aDL
        this.assignments = assignments
        return Triple(Delta_aDL, assignments, mutableSetOf())
    }
}