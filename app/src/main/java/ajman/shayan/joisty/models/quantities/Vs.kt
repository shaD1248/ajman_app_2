package ajman.shayan.joisty.models.quantities

import ajman.shayan.joisty.enums.Unit
import ajman.shayan.joisty.models.datasets.DataSet
import ajman.shayan.joisty.models.quantity_models.EvaluatableQuantity
import ajman.shayan.joisty.models.quantity_models.div
import ajman.shayan.joisty.models.quantity_models.times
import ajman.shayan.joisty.models.templates.Assignment
import java.lang.Math.PI
import java.lang.Math.cos
import java.lang.Math.sin

class Vs(dataSet: DataSet): EvaluatableQuantity(dataSet) {
    override val name = "Vs"
    override val dependencies = mutableSetOf("n", "Fy", "Asw")

    override fun evaluate(): Triple<Double, MutableList<Assignment>, MutableSet<String>> {
        actualDependencies = mutableSetOf(dataSet.fc, dataSet.bw, dataSet.d)

        val Vs: Double
        if (dataSet.hasConcreteWeb) {
            Vs = dataSet.n * dataSet.Fyw * dataSet.Asw * dataSet.d / dataSet.s * (sin(dataSet.alpha * PI / 180) + cos(dataSet.alpha * PI / 180))
            this.assignments.add(
                Assignment("V_s", Vs, Unit.TF, "n F_{yw} A_{sw} \\left(\\sin{\\alpha} + \\cos{\\alpha}\\right) \\frac{d}{s}")
            )
        } else {
            Vs = dataSet.n * dataSet.Fyw * dataSet.Asw * dataSet.d / dataSet.s * sin(dataSet.alpha * PI / 180)
            this.assignments.add(
                Assignment("V_s", Vs, Unit.TF, "n F_{yw} A_{sw} \\sin{\\alpha} \\frac{d}{s}")
            )
        }

        value = Vs
        return Triple(Vs, this.assignments, mutableSetOf())
    }
}