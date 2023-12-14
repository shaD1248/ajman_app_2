package ajman.shayan.joisty.models.quantities

import ajman.shayan.joisty.enums.Unit
import ajman.shayan.joisty.models.quantity_models.EvaluatableQuantity
import ajman.shayan.joisty.models.datasets.DataSet
import ajman.shayan.joisty.models.quantity_models.div
import ajman.shayan.joisty.models.quantity_models.times
import ajman.shayan.joisty.models.templates.Assignment
import kotlin.math.PI
import kotlin.math.sqrt

class fn(dataSet: DataSet): EvaluatableQuantity(dataSet) {
    override val name = "fn"
    override val dependencies = mutableSetOf("L", "Es", "I_composite", "g", "qD")

    override fun evaluate(): Triple<Double, MutableList<Assignment>, MutableSet<String>> {
        val assignments = mutableListOf<Assignment>()
        actualDependencies = mutableSetOf(dataSet.L, dataSet.Es, dataSet.I_composite, dataSet.g, dataSet.qD)
        val fn = PI / 2 / dataSet.L.pow(2) * sqrt(dataSet.Es * dataSet.I_composite * dataSet.g / dataSet.qD)
        assignments.add(Assignment("f_n", fn, Unit.HZ, "\\frac{\\pi}{2 L^2}\\sqrt{\\frac{E_s I_{composite} g}{q_D}}"))
        value = fn
        this.assignments = assignments
        return Triple(fn, assignments, mutableSetOf())
    }
}