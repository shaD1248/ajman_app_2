package ajman.shayan.joisty.models.quantities

import ajman.shayan.joisty.enums.Unit
import ajman.shayan.joisty.models.quantity_models.EvaluatableQuantity
import ajman.shayan.joisty.models.datasets.DataSet
import ajman.shayan.joisty.models.templates.Assignment

class Qst(dataSet: DataSet): EvaluatableQuantity(dataSet) {
    override val name = "Qst"
    override val dependencies = mutableSetOf("Ast", "d", "dj")

    override fun evaluate(): Triple<Double, MutableList<Assignment>, MutableSet<String>> {
        val assignments = mutableListOf<Assignment>()
        actualDependencies = mutableSetOf(dataSet.Ast, dataSet.d, dataSet.dj)
        val Qst = dataSet.Ast * (dataSet.d - dataSet.dj)
        assignments.add(Assignment("Q_{st}", Qst, Unit.CM3, "A_{st} \\left(d - d_j\\right)"))
        value = Qst
        this.assignments = assignments
        return Triple(Qst, assignments, mutableSetOf())
    }
}