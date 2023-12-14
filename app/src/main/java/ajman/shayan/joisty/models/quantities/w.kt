package ajman.shayan.joisty.models.quantities

import ajman.shayan.joisty.enums.Unit
import ajman.shayan.joisty.models.datasets.DataSet
import ajman.shayan.joisty.models.quantity_models.EvaluatableQuantity
import ajman.shayan.joisty.models.templates.Assignment

class w(dataSet: DataSet): EvaluatableQuantity(dataSet) {
    override val name = "w"
    override val dependencies = mutableSetOf("wD", "wL")

    override fun evaluate(): Triple<Double, MutableList<Assignment>, MutableSet<String>> {
        actualDependencies = mutableSetOf(dataSet.wD, dataSet.wL)
        val w: Double = dataSet.wD + dataSet.wL
        val assignments = mutableListOf(
            Assignment("w", w, Unit.KGF_OVER_M2, "w_D + w_L")
        )
        value = w
        this.assignments = assignments
        return Triple(w, assignments, mutableSetOf())
    }
}