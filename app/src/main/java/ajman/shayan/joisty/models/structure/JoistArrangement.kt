package ajman.shayan.joisty.models.structure

enum class JoistArrangement {
    SINGLE_50_WITH_CONCRETE_WEB,
    SINGLE_50_WITHOUT_CONCRETE_WEB,
    SINGLE_70_WITH_CONCRETE_WEB,
    SINGLE_70_WITHOUT_CONCRETE_WEB,
    DOUBLE_85_WITH_CONCRETE_WEB;

    fun get_n(): Int = when (this) {
        SINGLE_50_WITH_CONCRETE_WEB -> 1
        SINGLE_50_WITHOUT_CONCRETE_WEB -> 1
        SINGLE_70_WITH_CONCRETE_WEB -> 1
        SINGLE_70_WITHOUT_CONCRETE_WEB -> 1
        DOUBLE_85_WITH_CONCRETE_WEB -> 2
    }

    fun get_b(): Double = when (this) {
        SINGLE_50_WITH_CONCRETE_WEB -> 50 * cm
        SINGLE_50_WITHOUT_CONCRETE_WEB -> 50 * cm
        SINGLE_70_WITH_CONCRETE_WEB -> 70 * cm
        SINGLE_70_WITHOUT_CONCRETE_WEB -> 70 * cm
        DOUBLE_85_WITH_CONCRETE_WEB -> 85 * cm
    }

    fun get_bb(): Double = when (this) {
        SINGLE_50_WITH_CONCRETE_WEB -> 45 * cm
        SINGLE_50_WITHOUT_CONCRETE_WEB -> 45 * cm
        SINGLE_70_WITH_CONCRETE_WEB -> 66 * cm
        SINGLE_70_WITHOUT_CONCRETE_WEB -> 66 * cm
        DOUBLE_85_WITH_CONCRETE_WEB -> 66 * cm
    }

    fun hasConcreteWeb(): Boolean = when (this) {
        SINGLE_50_WITH_CONCRETE_WEB -> true
        SINGLE_50_WITHOUT_CONCRETE_WEB -> false
        SINGLE_70_WITH_CONCRETE_WEB -> true
        SINGLE_70_WITHOUT_CONCRETE_WEB -> false
        DOUBLE_85_WITH_CONCRETE_WEB -> true
    }

    override fun toString() = when (this) {
        SINGLE_50_WITH_CONCRETE_WEB -> "1 @ 50 cm + Concrete Web"
        SINGLE_50_WITHOUT_CONCRETE_WEB -> "1 @ 50 cm"
        SINGLE_70_WITH_CONCRETE_WEB -> "1 @ 70 cm + Concrete Web"
        SINGLE_70_WITHOUT_CONCRETE_WEB -> "1 @ 70 cm"
        DOUBLE_85_WITH_CONCRETE_WEB -> "2 @ 85 cm + Concrete Web"
    }
}
