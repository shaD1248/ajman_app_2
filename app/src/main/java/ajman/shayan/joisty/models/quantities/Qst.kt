package ajman.shayan.joisty.models.quantities

import ajman.shayan.joisty.enums.Unit
import ajman.shayan.joisty.models.quantity_models.EvaluatableQuantity
import ajman.shayan.joisty.models.datasets.DataSet
import ajman.shayan.joisty.models.templates.Assignment

class Qst(dataSet: DataSet): EvaluatableQuantity(dataSet) {
    override val name = "Qst"
    override val dependencies = mutableSetOf("Ast", "d", "dj")

    override fun evaluate(): Triple<Double, MutableList<Assignment>, MutableSet<String>> {
        val Ast = dataSet.Ast
        val d = dataSet.d
        val dj = dataSet.dj
        val assignments = mutableListOf<Assignment>()
        val Qst = Ast * (d - dj)
        assignments.add(Assignment("Q_{st}", Qst, Unit.CM3, "A_{st} \\left(d - d_j\\right)"))
        return Triple(Qst, assignments, mutableSetOf())
    }
}