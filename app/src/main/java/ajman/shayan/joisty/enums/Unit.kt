package ajman.shayan.joisty.enums

import ajman.shayan.joisty.models.structure.kgf
import ajman.shayan.joisty.models.structure.m
import ajman.shayan.joisty.models.structure.s

enum class Unit {
    CM, CM3, CM4, HZ, KGFM, KGF_OVER_M, KGF_OVER_M2, M, M3, TF, TFM, UNIT;

    override fun toString(): String = when (this) {
        CM -> "cm"
        CM3 -> "cm³"
        CM4 -> "cm^4"
        HZ -> "Hz"
        KGFM -> "kgfm"
        KGF_OVER_M -> "kgf/m"
        KGF_OVER_M2 -> "kgf/m²"
        M -> "m"
        M3 -> "m³"
        TF -> "Tf"
        TFM -> "Tfm"
        else -> ""
    }
    fun getValue(): Double = when (this) {
        CM -> 0.01 * m
        CM3 -> 10.0E-6 * m * m * m
        CM4 -> 10.0E-8 * m * m * m * m
        HZ -> 1.0 / s
        KGFM -> kgf * m
        KGF_OVER_M -> kgf / m
        KGF_OVER_M2 -> kgf / m / m
        M -> m
        M3 -> m * m * m
        TF -> 1000 * kgf
        TFM -> 1000 * kgf * m
        UNIT -> 1.0
    }
}