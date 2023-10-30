package ajman.shayan.joisty.models.quantities

import ajman.shayan.joisty.enums.Unit
import ajman.shayan.joisty.models.Quantity
import ajman.shayan.joisty.models.templates.Assignment
import kotlin.math.pow

class I_composite: Quantity() {
    override val name = "I_composite"
    override val dependencies = mutableSetOf(
        "be", "ns", "y_composite", "Asb", "Qsb", "bw", "h", "d", "Ast", "Qst"
    )

    override fun evaluate(dataSet: MutableMap<String, Double>): Pair<Double, MutableList<Assignment>> {
        val be = dataSet["be"] ?: 0.0
        val ns = dataSet["ns"] ?: 0.0
        val y_composite = dataSet["y_composite"] ?: 0.0
        val Asb = dataSet["Asb"] ?: 0.0
        val Qsb = dataSet["Qsb"] ?: 0.0
        val bw = dataSet["bw"] ?: 0.0
        val h = dataSet["h"] ?: 0.0
        val d = dataSet["d"] ?: 0.0
        val Ast = dataSet["Ast"] ?: 0.0
        val Qst = dataSet["Qst"] ?: 0.0
        val assignments = mutableListOf<Assignment>()
        var Ic = be / 3 / ns * y_composite.pow(3) + Asb * (Qsb / Asb - y_composite).pow(2)
        var formula = "\\frac{b_e}{3 n_s} y_{composite}^3 + A_{sb} \\left(\\frac{Q_{sb}}{A_{sb}} - y_{composite}\\right)^2"
        if (y_composite > h) {
            Ic += -(be - bw) / 3 / ns * (y_composite - h).pow(3)
            formula += " - \\frac{\\left(b_e - b_w\\right)}{3 n_s} \\left(y_{composite} - h\\right)^3"
        }
        val Isb = Asb * d.pow(2) - 2 * Qsb * y_composite + Asb * y_composite.pow(2)
        formula += " + A_{sb} d^2 - 2 Q_{sb} y_{composite} + A_{sb} y_{composite}^2"
        val Ist = Ast * d.pow(2) - 2 * Qst * y_composite + Ast * y_composite.pow(2)
        formula += " + A_{st} d^2 - 2 Q_{st} y_{composite} + A_{st} y_{composite}^2"
        val I_composite = Ic + Isb + Ist
        assignments.add(Assignment("I_{composite}", I_composite, Unit.CM4, formula))
        return Pair(I_composite, assignments)
    }
}