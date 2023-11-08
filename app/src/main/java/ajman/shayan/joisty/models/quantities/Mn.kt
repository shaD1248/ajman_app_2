package ajman.shayan.joisty.models.quantities

import ajman.shayan.joisty.enums.Unit
import ajman.shayan.joisty.models.quantity_models.EvaluatableQuantity
import ajman.shayan.joisty.models.datasets.DataSet
import ajman.shayan.joisty.models.templates.Assignment
import ajman.shayan.joisty.models.quantity_models.times
import kotlin.math.min
import kotlin.math.pow

class Mn(dataSet: DataSet): EvaluatableQuantity(dataSet) {
    override val name = "Mn"
    override val dependencies = mutableSetOf("h", "a", "be", "bw", "Mp", "fc", "Fy", "Sb", "Sc")

    override fun evaluate(): Triple<Double, MutableList<Assignment>, MutableSet<String>> {
        val assignments = mutableListOf<Assignment>()
        val hasConcreteWeb = dataSet.hasConcreteWeb
        val h = dataSet.h
        val a = dataSet.a
        val be = dataSet.be
        val bw = dataSet.bw
        val Mp = dataSet.Mp
        val fc = dataSet.fc
        val Fy = dataSet.Fy
        val Sb = dataSet.Sb
        val Sc = dataSet.Sc
        val formula: String?
        val Mn = if (hasConcreteWeb) {
            val Qc: Double = if (a <= h) {
                formula = "\\frac{1}{2} a^2 b_e"
                0.5 * a.pow(2) * be
            } else {
                formula = "\\frac{1}{2} a^2 b_w - \\frac{1}{2} \\left(b_e - b_w\\right) h^2"
                0.5 * a.pow(2) * bw - 0.5 * (be - bw) * h.pow(2)
            }
            assignments.add(Assignment("Q_c", Qc, Unit.CM3, formula))
            val Mn = Mp - 0.85 * fc * Qc
            assignments.add(Assignment("M_n", Mn, Unit.KGFM, "M_p - 0.85 f_c Q_c"))
            Mn
        } else {
            val Mn = min(Fy * Sb, -0.7 * fc * Sc)
            assignments.add(Assignment("M_n", Mn, Unit.KGFM,  "min(F_y S_b, 0.7 f_c S_c)"))
            Mn
        }
        return Triple(Mn, assignments, mutableSetOf())
    }
}