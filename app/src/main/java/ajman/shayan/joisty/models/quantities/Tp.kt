package ajman.shayan.joisty.models.quantities

import ajman.shayan.joisty.enums.Unit
import ajman.shayan.joisty.models.quantity_models.EvaluatableQuantity
import ajman.shayan.joisty.models.datasets.DataSet
import ajman.shayan.joisty.models.templates.Assignment

class Tp(dataSet: DataSet): EvaluatableQuantity(dataSet) {
    override val name = "Tp"
    override val dependencies = mutableSetOf("Asb", "Fy")

    override fun evaluate(): Triple<Double, MutableList<Assignment>, MutableSet<String>> {
        val Asb = dataSet.Asb
        val Fy = dataSet.Fy
        val assignments = mutableListOf<Assignment>()
        val Tp = Asb * Fy
        assignments.add(Assignment("T_p", Tp, Unit.TF, "A_{sb} F_y"))
        return Triple(Tp, assignments, mutableSetOf())
    }
}