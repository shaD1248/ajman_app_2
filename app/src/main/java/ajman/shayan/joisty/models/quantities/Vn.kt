package ajman.shayan.joisty.models.quantities

import ajman.shayan.joisty.enums.Unit
import ajman.shayan.joisty.models.quantity_models.EvaluatableQuantity
import ajman.shayan.joisty.models.datasets.DataSet
import ajman.shayan.joisty.models.templates.Assignment

class Vn(dataSet: DataSet): EvaluatableQuantity(dataSet) {
    override val name = "Vn"
    override val dependencies = mutableSetOf("Vc", "Vs")

    override fun evaluate(): Triple<Double, MutableList<Assignment>, MutableSet<String>> {
        actualDependencies = mutableSetOf(dataSet.Vc, dataSet.Vs)
        val Vn: Double = dataSet.Vc + dataSet.Vs
        this.assignments = mutableListOf(
            Assignment("V_n", Vn, Unit.TF, "V_c + V_s")
        )
        value = Vn
        return Triple(Vn, this.assignments, mutableSetOf())
    }
}