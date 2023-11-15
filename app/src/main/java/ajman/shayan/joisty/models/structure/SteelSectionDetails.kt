package ajman.shayan.joisty.models.structure

enum class SteelSectionDetails {
    PL_120_3,
    PL_120_4,
    PL_120_3_PL_3_120;

    override fun toString(): String = when (this) {
        PL_120_3 -> "Pl120x3 (H)"
        PL_120_4 -> "Pl120x4 (H)"
        PL_120_3_PL_3_120 -> "Pl120x3 (H) + Pl120x3 (V)"
    }

    fun get_Asb(): Double = when (this) {
        PL_120_3 -> 3.6 * cm2
        PL_120_4 -> 4.8 * cm2
        PL_120_3_PL_3_120 -> 7.2 * cm2
    }

    fun get_Ast(): Double = 0.18 * cm2

    fun get_t1(): Double = when (this) {
        PL_120_3 -> 0.3 * cm
        PL_120_4 -> 0.4 * cm
        PL_120_3_PL_3_120 -> 0.3 * cm
    }
}
