package ajman.shayan.joisty.models.quantities

import ajman.shayan.joisty.enums.Unit
import ajman.shayan.joisty.models.quantity_models.EvaluatableQuantity
import ajman.shayan.joisty.models.datasets.DataSet
import ajman.shayan.joisty.models.templates.Assignment

class Mp(dataSet: DataSet): EvaluatableQuantity(dataSet) {
    override val name = "Mp"
    override val dependencies = mutableSetOf("Asb", "Fy", "d")

    override fun evaluate(): Triple<Double, MutableList<Assignment>, MutableSet<String>> {
        val Asb = dataSet.Asb
        val Fy = dataSet.Fy
        val d = dataSet.d
        val assignments = mutableListOf<Assignment>()
        val Mp = Asb * Fy * d
        assignments.add(Assignment("M_p", Mp, Unit.KGFM, "A_{sb} F_y d"))
        return Triple(Mp, assignments, mutableSetOf())
    }
}