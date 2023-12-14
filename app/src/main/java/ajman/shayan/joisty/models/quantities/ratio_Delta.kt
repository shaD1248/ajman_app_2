package ajman.shayan.joisty.models.quantities

import ajman.shayan.joisty.enums.Unit
import ajman.shayan.joisty.models.datasets.DataSet
import ajman.shayan.joisty.models.quantity_models.EvaluatableQuantity
import ajman.shayan.joisty.models.templates.Assignment
import kotlin.math.max

class ratio_Delta(dataSet: DataSet): EvaluatableQuantity(dataSet) {
    override val name = "ratio_Delta"
    override val dependencies = mutableSetOf("Delta_DL", "Delta_aDL", "Delta_L", "Delta_aL")

    override fun evaluate(): Triple<Double, MutableList<Assignment>, MutableSet<String>> {
        actualDependencies = mutableSetOf(dataSet.Delta_DL, dataSet.Delta_aDL, dataSet.Delta_L, dataSet.Delta_aL)
        val ratio_Delta: Double = max(dataSet.Delta_DL / dataSet.Delta_aDL, dataSet.Delta_L / dataSet.Delta_aL)
        this.assignments = mutableListOf(
            Assignment("ratio_\\Delta", ratio_Delta, Unit.UNIT, "\\max\\left(\\frac{\\Delta_{D\\&L}}{\\Delta_{aD\\&L}},\\frac{\\Delta_{L}}{\\Delta_{aL}}\\right)")
        )
        value = ratio_Delta
        return Triple(ratio_Delta, this.assignments, mutableSetOf())
    }
}