package ajman.shd.app1.models.quantities

import ajman.shd.app1.enums.Unit
import ajman.shd.app1.models.Quantity
import ajman.shd.app1.models.templates.Assignment
import kotlin.math.min
import kotlin.math.pow

class Mn: Quantity() {
    override val name = "Mn"
    override val dependencies = mutableSetOf("h", "a", "be", "bw", "Mp", "fc", "Fy", "Sb", "Sc")

    override fun evaluate(dataSet: MutableMap<String, Double>): Pair<Double, MutableList<Assignment>> {
        val assignments = mutableListOf<Assignment>()
        val hasConcreteWeb = dataSet["hasConcreteWeb"] ?: 1.0
        val h = dataSet["h"] ?: 0.0
        val a = dataSet["a"] ?: 0.0
        val be = dataSet["be"] ?: 0.0
        val bw = dataSet["bw"] ?: 0.0
        val Mp = dataSet["Mp"] ?: 0.0
        val fc = dataSet["fc"] ?: 0.0
        val Fy = dataSet["Fy"] ?: 0.0
        val Sb = dataSet["Sb"] ?: 0.0
        val Sc = dataSet["Sc"] ?: 0.0
        val formula: String?
        val Mn = if (hasConcreteWeb == 1.0) {
            val Qc: Double = if (a <= h) {
                formula = "0.5 * a^2 * be"
                0.5 * a.pow(2) * be
            } else {
                formula = "0.5 * a^2 * bw - 0.5 * (be - bw) * h^2"
                0.5 * a.pow(2) * bw - 0.5 * (be - bw) * h.pow(2)
            }
            assignments.add(Assignment("Qc", Qc, Unit.CM3, formula))
            val Mn = Mp - 0.85 * fc * Qc
            assignments.add(Assignment("Mn", Mn, Unit.KGFM, "Mp - 0.85 * fc * Qc"))
            Mn
        } else {
            val Mn = min(Fy * Sb, -0.7 * fc * Sc)
            assignments.add(Assignment("Mn", Mn, Unit.KGFM,  "min(Fy * Sb, 0.7 * fc * Sc)"))
            Mn
        }
        return Pair(Mn, assignments)
    }
}