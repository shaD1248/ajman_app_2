package ajman.shayan.joisty.models.quantities

import ajman.shayan.joisty.enums.Unit
import ajman.shayan.joisty.models.quantity_models.EvaluatableQuantity
import ajman.shayan.joisty.models.datasets.DataSet
import ajman.shayan.joisty.models.quantity_models.compareTo
import ajman.shayan.joisty.models.quantity_models.div
import ajman.shayan.joisty.models.quantity_models.times
import ajman.shayan.joisty.models.solveQuadratic
import ajman.shayan.joisty.models.templates.Assignment

class y_composite(dataSet: DataSet): EvaluatableQuantity(dataSet) {
    override val name = "y_composite"
    override val dependencies = mutableSetOf("be", "ns", "Asb", "Qsb", "bw", "h")

    override fun evaluate(): Triple<Double, MutableList<Assignment>, MutableSet<String>> {
        val assignments = mutableListOf<Assignment>()
        actualDependencies =
            mutableSetOf(dataSet.be, dataSet.ns, dataSet.Asb, dataSet.Qsb, dataSet.h)
        var y_composite = solveQuadratic(
            dataSet.be / 2 / dataSet.ns,
            dataSet.Asb.value ?: 0.0,
            -(dataSet.Qsb.value ?: 0.0)
        )
        if (y_composite > dataSet.h) {
            actualDependencies.add(dataSet.bw)
            y_composite = solveQuadratic(
                dataSet.bw / 2 / dataSet.ns,
                dataSet.Asb + (dataSet.be - dataSet.bw) / dataSet.ns * dataSet.h,
                -(dataSet.Qsb + (dataSet.be - dataSet.bw) / 2 / dataSet.ns * dataSet.h.pow(2))
            )
        }
        assignments.add(Assignment("y_{composite}", y_composite, Unit.CM))
        value = y_composite
        this.assignments = assignments
        return Triple(y_composite, assignments, mutableSetOf())
    }
}