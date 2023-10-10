package ajman.shd.app1.enums

import ajman.shd.app1.models.structure.kgf
import ajman.shd.app1.models.structure.m

enum class Unit {
    CM, CM3, CM4, KGFM, KGF_OVER_M, M, M3, TF, UNIT;

    override fun toString(): String = when (this) {
        CM -> "cm"
        CM3 -> "cm³"
        CM4 -> "cm^4"
        KGFM -> "kgfm"
        KGF_OVER_M -> "kgf/m"
        M -> "m"
        M3 -> "m³"
        TF -> "Tf"
        else -> ""
    }
    fun getValue(): Double = when (this) {
        CM -> 0.01 * m
        CM3 -> 10.0E-6 * m * m * m
        CM4 -> 10.0E-8 * m * m * m * m
        KGFM -> kgf * m
        KGF_OVER_M -> kgf / m
        M -> m
        M3 -> m * m * m
        TF -> 1000 * kgf
        UNIT -> 1.0
    }
}