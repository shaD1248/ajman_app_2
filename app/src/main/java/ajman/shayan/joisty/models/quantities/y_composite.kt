package ajman.shayan.joisty.models.quantities

import ajman.shayan.joisty.enums.Unit
import ajman.shayan.joisty.models.quantity_models.EvaluatableQuantity
import ajman.shayan.joisty.models.datasets.DataSet
import ajman.shayan.joisty.models.quantity_models.div
import ajman.shayan.joisty.models.solveQuadratic
import ajman.shayan.joisty.models.templates.Assignment
import kotlin.math.pow

class y_composite(dataSet: DataSet): EvaluatableQuantity(dataSet) {
    override val name = "y_composite"
    override val dependencies = mutableSetOf("be", "ns", "Asb", "Qsb", "bw", "h")

    override fun evaluate(): Triple<Double, MutableList<Assignment>, MutableSet<String>> {
        val be = dataSet.be
        val ns = dataSet.ns
        val Asb = dataSet.Asb
        val Qsb = dataSet.Qsb
        val bw = dataSet.bw
        val h = dataSet.h
        val assignments = mutableListOf<Assignment>()
        var y_composite = solveQuadratic(be / 2 / ns, Asb, -Qsb)
        if (y_composite > h) {
            y_composite = solveQuadratic(
                bw / 2 / ns,
                Asb + (be - bw) / ns * h,
                -(Qsb + (be - bw) / 2 / ns * h.pow(2))
            )
        }
        assignments.add(Assignment("y_{composite}", y_composite, Unit.CM))
        return Triple(y_composite, assignments, mutableSetOf())
    }
}