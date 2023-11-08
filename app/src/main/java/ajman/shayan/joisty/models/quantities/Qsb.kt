package ajman.shayan.joisty.models.quantities

import ajman.shayan.joisty.enums.Unit
import ajman.shayan.joisty.models.quantity_models.EvaluatableQuantity
import ajman.shayan.joisty.models.datasets.DataSet
import ajman.shayan.joisty.models.templates.Assignment

class Qsb(dataSet: DataSet): EvaluatableQuantity(dataSet) {
    override val name = "Qsb"
    override val dependencies = mutableSetOf("Asb", "d")

    override fun evaluate(): Triple<Double, MutableList<Assignment>, MutableSet<String>> {
        val assignments = mutableListOf<Assignment>()
        actualDependencies = mutableSetOf(dataSet.Asb, dataSet.d)
        val Qsb = dataSet.Asb * dataSet.d
        assignments.add(Assignment("Q_{sb}", Qsb, Unit.CM3, "A_{sb} d"))
        value = Qsb
        this.assignments = assignments
        return Triple(Qsb, assignments, mutableSetOf())
    }
}