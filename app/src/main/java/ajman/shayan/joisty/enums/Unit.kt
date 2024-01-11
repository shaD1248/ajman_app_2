package ajman.shayan.joisty.enums

import ajman.shayan.joisty.models.structure.kgf
import ajman.shayan.joisty.models.structure.m
import ajman.shayan.joisty.models.structure.s

enum class Unit {
    CM, CM2, CM3, CM4, HZ, KGF, KGFM, KGF_OVER_M, KGF_OVER_M2, KGF_OVER_M3, M, M3, TF, TFM, UNIT,
    UNIT_OVER_M2;

    override fun toString(): String = when (this) {
        CM -> "cm"
        CM2 -> "cm²"
        CM3 -> "cm³"
        CM4 -> "cm^4"
        HZ -> "Hz"
        KGF -> "kgf"
        KGFM -> "kgfm"
        KGF_OVER_M -> "kgf/m"
        KGF_OVER_M2 -> "kgf/m²"
        KGF_OVER_M3 -> "kgf/m³"
        M -> "m"
        M3 -> "m³"
        TF -> "Tf"
        TFM -> "Tfm"
        UNIT_OVER_M2 -> "1/m²"
        else -> ""
    }
    fun getValue(): Double = when (this) {
        CM -> 0.01 * m
        CM2 -> 0.0001 * m * m
        CM3 -> 10.0E-6 * m * m * m
        CM4 -> 10.0E-8 * m * m * m * m
        HZ -> 1.0 / s
        KGF -> kgf
        KGFM -> kgf * m
        KGF_OVER_M -> kgf / m
        KGF_OVER_M2 -> kgf / m / m
        KGF_OVER_M3 -> kgf / m / m / m
        M -> m
        M3 -> m * m * m
        TF -> 1000 * kgf
        TFM -> 1000 * kgf * m
        UNIT -> 1.0
        UNIT_OVER_M2 -> 1.0 / m / m
    }
}