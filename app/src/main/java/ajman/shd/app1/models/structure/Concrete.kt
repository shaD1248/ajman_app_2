package ajman.shd.app1.models.structure

import kotlin.math.pow

class Concrete(override val F:Double): Material(F, calculate_E(F))

private fun calculate_E(F: Double): Double {
    return (3300 * (F * MPa).pow(0.5) + 6900 * MPa) * (24.0 / 23).pow(1.5)
}