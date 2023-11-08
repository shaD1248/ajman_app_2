package ajman.shayan.joisty.models.quantities

import ajman.shayan.joisty.enums.Unit
import ajman.shayan.joisty.models.quantity_models.EvaluatableQuantity
import ajman.shayan.joisty.models.datasets.DataSet
import ajman.shayan.joisty.models.templates.Assignment
import ajman.shayan.joisty.models.quantity_models.times
import kotlin.math.min

class Mn(dataSet: DataSet): EvaluatableQuantity(dataSet) {
    override val name = "Mn"
    override val dependencies = mutableSetOf("h", "a", "be", "bw", "Mp", "fc", "Fy", "Sb", "Sc")

    override fun evaluate(): Triple<Double, MutableList<Assignment>, MutableSet<String>> {
        val assignments = mutableListOf<Assignment>()
        val hasConcreteWeb = dataSet.hasConcreteWeb
        val formula: String?
        val Mn = if (hasConcreteWeb) {
            actualDependencies += mutableSetOf(dataSet.a, dataSet.h, dataSet.be, dataSet.Mp, dataSet.fc)
            val Qc: Double = if (dataSet.a <= dataSet.h) {
                formula = "\\frac{1}{2} a^2 b_e"
                0.5 * dataSet.a.pow(2) * dataSet.be
            } else {
                actualDependencies.add(dataSet.bw)
                formula = "\\frac{1}{2} a^2 b_w - \\frac{1}{2} \\left(b_e - b_w\\right) h^2"
                0.5 * dataSet.a.pow(2) * dataSet.bw - 0.5 * (dataSet.be - dataSet.bw) * dataSet.h.pow(
                    2
                )
            }
            assignments.add(Assignment("Q_c", Qc, Unit.CM3, formula))
            val Mn = dataSet.Mp - 0.85 * dataSet.fc * Qc
            assignments.add(Assignment("M_n", Mn, Unit.KGFM, "M_p - 0.85 f_c Q_c"))
            Mn
        } else {
            actualDependencies += mutableSetOf(dataSet.Fy, dataSet.Sb, dataSet.fc, dataSet.Sc)
            val Mn = min(dataSet.Fy * dataSet.Sb, -0.7 * dataSet.fc * dataSet.Sc)
            assignments.add(Assignment("M_n", Mn, Unit.KGFM,  "min(F_y S_b, 0.7 f_c S_c)"))
            Mn
        }
        value = Mn
        this.assignments = assignments
        return Triple(Mn, assignments, mutableSetOf())
    }
}