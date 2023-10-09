package ajman.shd.app1.enums

import ajman.shd.app1.models.structure.kgf
import ajman.shd.app1.models.structure.m

enum class Unit {
    CM, CM3, KGFM, KGF_OVER_M, M, M3, UNIT;

    override fun toString(): String = when (this) {
        CM -> "cm"
        CM3 -> "cm³"
        KGFM -> "kgfm"
        KGF_OVER_M -> "kgf/m"
        M -> "m"
        M3 -> "m³"
        else -> ""
    }
    fun getValue(): Double = when (this) {
        CM -> 0.01 * m
        CM3 -> 10.0E-6 * m * m * m
        KGFM -> kgf * m
        KGF_OVER_M -> kgf / m
        M -> m
        M3 -> m * m * m
        UNIT -> 1.0
    }
}