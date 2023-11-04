package ajman.shayan.joisty.models.quantities

import ajman.shayan.joisty.enums.Unit
import ajman.shayan.joisty.models.Quantity
import ajman.shayan.joisty.models.templates.Assignment

class a: Quantity() {
    override val name = "a"
    override val dependencies = mutableSetOf("Tp", "fc", "be", "h", "bw")

    override fun evaluate(dataSet: MutableMap<String, Double>): Pair<Double, MutableList<Assignment>> {
        val Tp = dataSet["Tp"] ?: 0.0
        val fc = dataSet["fc"] ?: 0.0
        val be = dataSet["be"] ?: 0.0
        val h = dataSet["h"] ?: 0.0
        val bw = dataSet["bw"] ?: 0.0
        val assignments = mutableListOf<Assignment>()
        var a = Tp / 0.85 / fc / be
        assignments.add(Assignment("a", a, Unit.CM, "\\frac{T_p}{0.85 f_c b_e}"))
        if (a > h) {
            a = Tp / 0.85 / fc / bw - (be / bw - 1) * h
            assignments.add(Assignment("a", a, Unit.CM, "\\frac{T_p}{0.85 f_c b_w} - \\left(\\frac{b_e}{b_w} - 1\\right) h"))
        }
        return Pair(a, assignments)
    }
}