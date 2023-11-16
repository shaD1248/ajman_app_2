package ajman.shayan.joisty.models.quantities

import ajman.shayan.joisty.enums.Unit
import ajman.shayan.joisty.models.quantity_models.EvaluatableQuantity
import ajman.shayan.joisty.models.datasets.DataSet
import ajman.shayan.joisty.models.quantity_models.div
import ajman.shayan.joisty.models.templates.Assignment

class ratio_v(dataSet: DataSet): EvaluatableQuantity(dataSet) {
    override val name = "ratio_v"
    override val dependencies = mutableSetOf("Vu", "phi_v", "Vn")

    override fun evaluate(): Triple<Double, MutableList<Assignment>, MutableSet<String>> {
        actualDependencies = mutableSetOf(dataSet.Vu, dataSet.phi_v, dataSet.Vn)
        val ratio_v: Double = dataSet.Vu / dataSet.phi_v / dataSet.Vn
        this.assignments = mutableListOf(
            Assignment("ratio_v", ratio_v, Unit.UNIT, "\\frac{V_u}{\\phi_v V_n}")
        )
        value = ratio_v
        return Triple(ratio_v, this.assignments, mutableSetOf())
    }
}