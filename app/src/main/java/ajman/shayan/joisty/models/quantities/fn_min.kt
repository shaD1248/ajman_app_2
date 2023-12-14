package ajman.shayan.joisty.models.quantities

import ajman.shayan.joisty.enums.Unit
import ajman.shayan.joisty.models.quantity_models.EvaluatableQuantity
import ajman.shayan.joisty.models.datasets.DataSet
import ajman.shayan.joisty.models.structure.s
import ajman.shayan.joisty.models.templates.Assignment

class fn_min(dataSet: DataSet): EvaluatableQuantity(dataSet) {
    override val name = "fn_min"
    override val dependencies = mutableSetOf<String>()

    override fun evaluate(): Triple<Double, MutableList<Assignment>, MutableSet<String>> {
        actualDependencies = mutableSetOf()
        val fn_min: Double = 5.0 / s
        val assignments = mutableListOf(
            Assignment("f_{n min}", fn_min, Unit.HZ)
        )
        value = fn_min
        this.assignments = assignments
        return Triple(fn_min, assignments, mutableSetOf())
    }
}