package ajman.shayan.joisty.models.quantities

import ajman.shayan.joisty.enums.Unit
import ajman.shayan.joisty.models.datasets.DataSet
import ajman.shayan.joisty.models.quantity_models.EvaluatableQuantity
import ajman.shayan.joisty.models.templates.Assignment

class wD(dataSet: DataSet): EvaluatableQuantity(dataSet) {
    override val name = "wD"
    override val dependencies = mutableSetOf("wsD", "wcD")

    override fun evaluate(): Triple<Double, MutableList<Assignment>, MutableSet<String>> {
        val wD: Double
        var formula: String? = null
        if (dataSet.shouldAddSelfWeight) {
            wD = dataSet.wsD + dataSet.wcD
            formula = "w_{sD} + w_{cD}"
        } else {
            wD = dataSet.wsD.value ?: 0.0
        }
        actualDependencies = mutableSetOf(dataSet.wD, dataSet.wL)
        this.assignments = mutableListOf(
            Assignment("w_D", wD, Unit.KGF_OVER_M2, formula)
        )
        value = wD
        return Triple(wD, this.assignments, mutableSetOf())
    }
}