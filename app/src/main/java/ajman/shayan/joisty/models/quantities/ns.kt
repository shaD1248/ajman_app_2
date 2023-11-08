package ajman.shayan.joisty.models.quantities

import ajman.shayan.joisty.enums.Unit
import ajman.shayan.joisty.models.quantity_models.EvaluatableQuantity
import ajman.shayan.joisty.models.datasets.DataSet
import ajman.shayan.joisty.models.templates.Assignment

class ns(dataSet: DataSet): EvaluatableQuantity(dataSet) {
    override val name = "ns"
    override val dependencies = mutableSetOf("Es", "Ec")

    override fun evaluate(): Triple<Double, MutableList<Assignment>, MutableSet<String>> {
        val assignments = mutableListOf<Assignment>()
        actualDependencies = mutableSetOf(dataSet.Es, dataSet.Ec)
        val ns = dataSet.Es / dataSet.Ec
        assignments.add(Assignment("n_s", ns, Unit.UNIT, "\\frac{E_s}{E_c}"))
        value = ns
        this.assignments = assignments
        return Triple(ns, assignments, mutableSetOf())
    }
}