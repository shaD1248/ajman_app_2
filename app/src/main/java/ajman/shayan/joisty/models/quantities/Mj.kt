package ajman.shayan.joisty.models.quantities

import ajman.shayan.joisty.enums.Unit
import ajman.shayan.joisty.models.datasets.DataSet
import ajman.shayan.joisty.models.quantity_models.EvaluatableQuantity
import ajman.shayan.joisty.models.structure.gamma_s
import ajman.shayan.joisty.models.templates.Assignment
import kotlin.math.cos

class Mj(dataSet: DataSet): EvaluatableQuantity(dataSet) {
    override val name = "Mj"
    override val dependencies = mutableSetOf("Asb", "Ast", "Asw", "alpha", "L")

    override fun evaluate(): Triple<Double, MutableList<Assignment>, MutableSet<String>> {
        val Mj = gamma_s * (
                dataSet.Asb * dataSet.L +
                        dataSet.Ast * dataSet.L +
                        dataSet.Asw * dataSet.L / cos(dataSet.alpha.value ?: 0.0)
                )
        actualDependencies = mutableSetOf(
            dataSet.Asb, dataSet.Ast, dataSet.Asw, dataSet.alpha, dataSet.L,
        )
        this.assignments = mutableListOf(Assignment("M_j", Mj, Unit.KGF))
        value = Mj
        return Triple(Mj, this.assignments, mutableSetOf())
    }
}