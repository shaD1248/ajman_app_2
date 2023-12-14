package ajman.shayan.joisty.models.quantities

import ajman.shayan.joisty.enums.Unit
import ajman.shayan.joisty.models.quantity_models.EvaluatableQuantity
import ajman.shayan.joisty.models.datasets.DataSet
import ajman.shayan.joisty.models.quantity_models.times
import ajman.shayan.joisty.models.templates.Assignment
import kotlin.math.max

class wu(dataSet: DataSet): EvaluatableQuantity(dataSet) {
    override val name = "wu"
    override val dependencies = mutableSetOf("wD", "wL")

    override fun evaluate(): Triple<Double, MutableList<Assignment>, MutableSet<String>> {
        actualDependencies = mutableSetOf(dataSet.wD, dataSet.wL)
        val wu: Double = max(1.4 * dataSet.wD, 1.2 * dataSet.wD + 1.6 * dataSet.wL)
        val assignments = mutableListOf(
            Assignment("w_u", wu, Unit.KGF_OVER_M2, "max\\left(1.4 w_D, 1.2 w_D + 1.6 w_L\\right)")
        )
        value = wu
        this.assignments = assignments
        return Triple(wu, assignments, mutableSetOf())
    }
}