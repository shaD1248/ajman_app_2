package ajman.shayan.joisty.models.structure

enum class SteelSectionDetails {
    P_120_3,
    P_120_4,
    P_120_3_P_3_120;

    fun get_Asb(): Double = when (this) {
        P_120_3 -> 3.6 * cm2
        P_120_4 -> 4.8 * cm2
        P_120_3_P_3_120 -> 7.2 * cm2
    }

    fun get_Ast(): Double = 0.18 * cm2
}
