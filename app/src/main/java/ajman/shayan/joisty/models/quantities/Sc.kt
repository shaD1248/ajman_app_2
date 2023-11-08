package ajman.shayan.joisty.models.quantities

import ajman.shayan.joisty.enums.Unit
import ajman.shayan.joisty.models.quantity_models.EvaluatableQuantity
import ajman.shayan.joisty.models.datasets.DataSet
import ajman.shayan.joisty.models.quantity_models.div
import ajman.shayan.joisty.models.templates.Assignment
import ajman.shayan.joisty.models.quantity_models.times

class Sc(dataSet: DataSet): EvaluatableQuantity(dataSet) {
    override val name = "Sc"
    override val dependencies = mutableSetOf("ns", "I_composite", "y_composite")

    override fun evaluate(): Triple<Double, MutableList<Assignment>, MutableSet<String>> {
        val assignments = mutableListOf<Assignment>()
        actualDependencies = mutableSetOf(dataSet.ns, dataSet.I_composite, dataSet.y_composite)
        val Sc = -dataSet.ns * dataSet.I_composite / dataSet.y_composite
        assignments.add(
            Assignment(
                "S_c",
                Sc,
                Unit.CM3,
                "-\\frac{n_s I_{composite}}{y_{composite}}"
            )
        )
        value = Sc
        this.assignments = assignments
        return Triple(Sc, assignments, mutableSetOf())
    }
}