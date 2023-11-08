package ajman.shayan.joisty.models.quantities

import ajman.shayan.joisty.enums.Unit
import ajman.shayan.joisty.models.quantity_models.EvaluatableQuantity
import ajman.shayan.joisty.models.datasets.DataSet
import ajman.shayan.joisty.models.quantity_models.div
import ajman.shayan.joisty.models.quantity_models.minus
import ajman.shayan.joisty.models.templates.Assignment
import ajman.shayan.joisty.models.quantity_models.times
import kotlin.math.pow

class I_composite(dataSet: DataSet): EvaluatableQuantity(dataSet) {
    override val name = "I_composite"
    override val dependencies = mutableSetOf(
        "be", "ns", "y_composite", "Asb", "Qsb", "bw", "h", "d", "Ast", "Qst"
    )

    override fun evaluate(): Triple<Double, MutableList<Assignment>, MutableSet<String>> {
        val be = dataSet.be
        val ns = dataSet.ns
        val y_composite = dataSet.y_composite
        val Asb = dataSet.Asb
        val Qsb = dataSet.Qsb
        val bw = dataSet.bw
        val h = dataSet.h
        val d = dataSet.d
        val Ast = dataSet.Ast
        val Qst = dataSet.Qst
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
        return Triple(I_composite, assignments, mutableSetOf())
    }
}