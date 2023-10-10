package ajman.shd.app1.models.quantities

import ajman.shd.app1.enums.Unit
import ajman.shd.app1.models.Quantity
import ajman.shd.app1.models.solveQuadratic
import ajman.shd.app1.models.templates.Assignment
import kotlin.math.pow

class y_composite: Quantity() {
    override val name = "y_composite"
    override val dependencies = mutableSetOf("be", "ns", "Asb", "Qsb", "bw", "h")

    override fun evaluate(dataSet: MutableMap<String, Double>): Pair<Double, MutableList<Assignment>> {
        val be = dataSet["be"] ?: 0.0
        val ns = dataSet["ns"] ?: 0.0
        val Asb = dataSet["Asb"] ?: 0.0
        val Qsb = dataSet["Qsb"] ?: 0.0
        val bw = dataSet["bw"] ?: 0.0
        val h = dataSet["h"] ?: 0.0
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
        return Pair(y_composite, assignments)
    }
}