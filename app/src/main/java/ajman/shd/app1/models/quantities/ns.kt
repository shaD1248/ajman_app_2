package ajman.shd.app1.models.quantities

import ajman.shd.app1.enums.Unit
import ajman.shd.app1.models.Quantity
import ajman.shd.app1.models.templates.Assignment

class ns: Quantity() {
    override val name = "ns"
    override val dependencies = mutableSetOf("Es", "Ec")

    override fun evaluate(dataSet: MutableMap<String, Double>): Pair<Double, MutableList<Assignment>> {
        val Es = dataSet["Es"] ?: 0.0
        val Ec = dataSet["Ec"] ?: 0.0
        val assignments = mutableListOf<Assignment>()
        val ns = Es / Ec
        assignments.add(Assignment("n_s", ns, Unit.UNIT, "\\frac{E_s}{E_c}"))
        return Pair(ns, assignments)
    }
}