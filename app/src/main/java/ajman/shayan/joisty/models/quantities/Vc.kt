package ajman.shayan.joisty.models.quantities

import ajman.shayan.joisty.enums.Unit
import ajman.shayan.joisty.models.quantity_models.EvaluatableQuantity
import ajman.shayan.joisty.models.datasets.DataSet
import ajman.shayan.joisty.models.templates.Assignment
import ajman.shayan.joisty.models.quantity_models.times
import ajman.shayan.joisty.models.structure.MPa
import java.lang.Math.sqrt

class Vc(dataSet: DataSet): EvaluatableQuantity(dataSet) {
    override val name = "Vc"
    override val dependencies = mutableSetOf("fc", "bw", "d")

    override fun evaluate(): Triple<Double, MutableList<Assignment>, MutableSet<String>> {
        actualDependencies = mutableSetOf(dataSet.fc, dataSet.bw, dataSet.d)
        val Vc: Double = 1 / 6 * sqrt(dataSet.fc * MPa) * dataSet.bw * dataSet.d
        val assignments = mutableListOf(
            Assignment("V_c", Vc, Unit.TF, "\\frac{1}{6} \\sqrt{f_c \\cdot MPa} b_w d")
        )
        value = Vc
        this.assignments = assignments
        return Triple(Vc, assignments, mutableSetOf())
    }
}