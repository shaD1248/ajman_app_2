package ajman.shayan.joisty.models.quantities

import ajman.shayan.joisty.enums.Unit
import ajman.shayan.joisty.models.datasets.DataSet
import ajman.shayan.joisty.models.quantity_models.EvaluatableQuantity
import ajman.shayan.joisty.models.quantity_models.div
import ajman.shayan.joisty.models.quantity_models.times
import ajman.shayan.joisty.models.structure.cm
import ajman.shayan.joisty.models.structure.gamma_b
import ajman.shayan.joisty.models.structure.gamma_c
import ajman.shayan.joisty.models.structure.gamma_rc
import ajman.shayan.joisty.models.structure.gamma_s
import ajman.shayan.joisty.models.structure.kgf
import ajman.shayan.joisty.models.structure.m2
import ajman.shayan.joisty.models.templates.Assignment
import kotlin.math.ceil
import kotlin.math.cos
import kotlin.math.pow

class wcD(dataSet: DataSet): EvaluatableQuantity(dataSet) {
    override val name = "wcD"
    override val dependencies = mutableSetOf("wsD", "wcD")

    val et = 8 * cm
    var beta_1: Double = 0.0
    var beta_2: Double = 0.0
    var hs: Double = 0.0
    var sigma_j: Double = 0.0
    var sigma_s: Double = 0.0
    var sigma_w: Double = 0.0
    var sigma_t: Double = 0.0
    var sigma_c: Double = 0.0
    var sigma_b: Double = 0.0
    var sigma: Double = 0.0

    override fun evaluate(): Triple<Double, MutableList<Assignment>, MutableSet<String>> {
        val Mj = gamma_s * (
                dataSet.Asb * dataSet.L +
                        dataSet.Ast * dataSet.L +
                        dataSet.Asw * dataSet.L / cos(dataSet.alpha.value ?: 0.0)
                )
        beta_1 = dataSet.bw / dataSet.b
        beta_2 = 0.04
        hs = if (dataSet.hasConcreteWeb) 2 * cm else 0.0
        sigma_j = dataSet.n * Mj / dataSet.b / dataSet.L
        sigma_s = gamma_rc * dataSet.h
        sigma_w = gamma_c * beta_1 * (dataSet.d - dataSet.h)
        sigma_t = gamma_rc * (1 - beta_1) * beta_2 * (dataSet.d - dataSet.h)
        sigma_c = (gamma_c - gamma_b) * (1 - beta_2) * et.pow(2) / dataSet.b
        sigma_b = gamma_b * (1 - beta_1) * (1 - beta_2) * (dataSet.d - dataSet.h + hs)
        sigma = sigma_j + sigma_s + sigma_w + sigma_t + sigma_c + sigma_b
        val precision = 5 * kgf / m2
        val wcD = ceil(sigma / precision) * precision
        actualDependencies = mutableSetOf(
            dataSet.b, dataSet.bw, dataSet.d, dataSet.h, dataSet.L,
        )
        this.assignments = mutableListOf(Assignment("w_{cD}", wcD, Unit.KGF_OVER_M2))
        value = wcD
        return Triple(wcD, this.assignments, mutableSetOf())
    }
}