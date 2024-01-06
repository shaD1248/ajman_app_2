package ajman.shayan.joisty.models.structure

import kotlin.math.ceil
import kotlin.math.cos
import kotlin.math.pow

class AreaLoading(var occupancy: Occupancy) {
    var shouldAddSelfWeight: Boolean = occupancy.shouldAddSelfWeight
    private var wsD: Double = occupancy.wsD
    var wL: Double = occupancy.wL
    private var wcD: Double = 0.0
    fun calculateSelfWeight(compositeJoist: CompositeJoist) {
        val steelJoist = compositeJoist.steelJoist
        val Mj = gamma_s * (
                steelJoist.bottomComponent.A * steelJoist.bottomComponent.L +
                steelJoist.topComponent.A * steelJoist.topComponent.L +
                steelJoist.webComponent.A * compositeJoist.L / cos(steelJoist.webComponent.alpha)
                )
        val beta_1 = compositeJoist.bw / compositeJoist.b
        val beta_2 = 0.04
        val et = 8 * cm
        val hs = if (compositeJoist.joistArrangement.hasConcreteWeb()) 2 * cm else 0.0
        val sigma_j = steelJoist.n * Mj / compositeJoist.b / compositeJoist.L
        val sigma_s = gamma_rc * compositeJoist.h
        val sigma_w = gamma_c * beta_1 * (compositeJoist.d - compositeJoist.h)
        val sigma_t = gamma_rc * (1 - beta_1) * beta_2 * (compositeJoist.d - compositeJoist.h)
        val sigma_c = (gamma_c - gamma_b) * (1 - beta_2) * et.pow(2) / compositeJoist.b
        val sigma_b = gamma_b * (1 - beta_1) * (1 - beta_2) * (compositeJoist.d - compositeJoist.h + hs)
        val sigma = sigma_j + sigma_s + sigma_w + sigma_t + sigma_c + sigma_b
        val precision = 5 * kgf / m2
        wcD = ceil(sigma / precision) * precision
    }

    val wD: Double get() = if (shouldAddSelfWeight) wsD + wcD else wsD
}