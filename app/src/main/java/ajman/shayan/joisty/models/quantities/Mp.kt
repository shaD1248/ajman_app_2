package ajman.shayan.joisty.models.quantities

import ajman.shayan.joisty.enums.Unit
import ajman.shayan.joisty.models.quantity_models.EvaluatableQuantity
import ajman.shayan.joisty.models.datasets.DataSet
import ajman.shayan.joisty.models.quantity_models.times
import ajman.shayan.joisty.models.templates.Assignment

class Mp(dataSet: DataSet): EvaluatableQuantity(dataSet) {
    override val name = "Mp"
    override val dependencies = mutableSetOf("Asb", "Fy", "d")

    override fun evaluate(): Triple<Double, MutableList<Assignment>, MutableSet<String>> {
        val assignments = mutableListOf<Assignment>()
        actualDependencies = mutableSetOf(dataSet.Asb, dataSet.Fy, dataSet.d)
        val Mp = dataSet.Asb * dataSet.Fy * dataSet.d
        assignments.add(Assignment("M_p", Mp, Unit.TFM, "A_{sb} F_y d"))
        value = Mp
        this.assignments = assignments
        return Triple(Mp, assignments, mutableSetOf())
    }
}