package ajman.shayan.joisty.models.structure

import kotlin.math.PI
import kotlin.math.pow

enum class SteelWebDetails {
    T14_20;

    override fun toString(): String = when (this) {
        T14_20 -> "Ø14 @ 20 cm"
    }

    fun get_D(): Double = when (this) {
        T14_20 -> 1.4 * cm
    }

    fun get_s(): Double = when (this) {
        T14_20 -> 20 * cm
    }

    fun get_Asw(): Double = PI / 4 * get_D().pow(2)
}
