package ajman.shayan.joisty.models.quantities

import ajman.shayan.joisty.enums.Unit
import ajman.shayan.joisty.models.datasets.DataSet
import ajman.shayan.joisty.models.quantity_models.EvaluatableQuantity
import ajman.shayan.joisty.models.templates.Assignment

class ratio_f(dataSet: DataSet): EvaluatableQuantity(dataSet) {
    override val name = "ratio_f"
    override val dependencies = mutableSetOf("fn_min", "fn")

    override fun evaluate(): Triple<Double, MutableList<Assignment>, MutableSet<String>> {
        actualDependencies = mutableSetOf(dataSet.fn_min, dataSet.fn)
        val ratio_f: Double = dataSet.fn_min / dataSet.fn
        this.assignments = mutableListOf(
            Assignment("ratio_f", ratio_f, Unit.UNIT, "\\frac{f_{n min}}{f_n}")
        )
        value = ratio_f
        return Triple(ratio_f, this.assignments, mutableSetOf())
    }
}