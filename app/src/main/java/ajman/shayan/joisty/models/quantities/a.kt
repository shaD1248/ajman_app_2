package ajman.shayan.joisty.models.quantities

import ajman.shayan.joisty.enums.Unit
import ajman.shayan.joisty.models.quantity_models.EvaluatableQuantity
import ajman.shayan.joisty.models.datasets.DataSet
import ajman.shayan.joisty.models.templates.Assignment

class a(dataSet: DataSet): EvaluatableQuantity(dataSet) {
    override val name = "a"
    override val dependencies = mutableSetOf("Tp", "fc", "be", "h", "bw")

    override fun evaluate(): Triple<Double, MutableList<Assignment>, MutableSet<String>> {
        val assignments = mutableListOf<Assignment>()
        var a = dataSet.Tp / 0.85 / dataSet.fc / dataSet.be
        assignments.add(Assignment("a", a, Unit.CM, "\\frac{T_p}{0.85 f_c b_e}"))
        if (a > dataSet.h) {
            a = dataSet.Tp / 0.85 / dataSet.fc / dataSet.bw - (dataSet.be / dataSet.bw - 1) * dataSet.h
            assignments.add(Assignment("a", a, Unit.CM, "\\frac{T_p}{0.85 f_c b_w} - \\left(\\frac{b_e}{b_w} - 1\\right) h"))
        }
        value = a
        return Triple(a, assignments, mutableSetOf())
    }
}