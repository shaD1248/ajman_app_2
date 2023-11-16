package ajman.shayan.joisty.models.quantities

import ajman.shayan.joisty.enums.Unit
import ajman.shayan.joisty.models.quantity_models.EvaluatableQuantity
import ajman.shayan.joisty.models.datasets.DataSet
import ajman.shayan.joisty.models.templates.Assignment

class phi_v(dataSet: DataSet): EvaluatableQuantity(dataSet) {
    override val name = "phi_v"
    override val dependencies = mutableSetOf("hasConcreteWeb")

    override fun evaluate(): Triple<Double, MutableList<Assignment>, MutableSet<String>> {
        val phi_v = if (dataSet.hasConcreteWeb) 0.75 else 0.9
        this.assignments = mutableListOf(
            Assignment("\\phi_v", phi_v, Unit.UNIT, null)
        )
        value = phi_v
        return Triple(phi_v, this.assignments, mutableSetOf())
    }
}