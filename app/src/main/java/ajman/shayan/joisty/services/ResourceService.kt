package ajman.shayan.joisty.services

import ajman.shayan.joisty.R
import ajman.shayan.joisty.models.structure.JoistArrangement
import ajman.shayan.joisty.models.structure.Occupancy

fun getResourceIdForEnum(enum: Enum<*>): Int = when (enum) {
    Occupancy.COMMERCIAL -> R.string.occupancy_commercial
    Occupancy.PARKING -> R.string.occupancy_parking
    Occupancy.RESIDENTIAL -> R.string.occupancy_residential
    Occupancy.ROOF -> R.string.occupancy_roof
    JoistArrangement.SINGLE_50_WITH_CONCRETE_WEB -> R.string.joist_arrangement_single_50_with_concrete_web
    JoistArrangement.SINGLE_50_WITHOUT_CONCRETE_WEB -> R.string.joist_arrangement_single_50_without_concrete_web
    JoistArrangement.SINGLE_70_WITH_CONCRETE_WEB -> R.string.joist_arrangement_single_70_with_concrete_web
    JoistArrangement.SINGLE_70_WITHOUT_CONCRETE_WEB -> R.string.joist_arrangement_single_70_without_concrete_web
    JoistArrangement.DOUBLE_85_WITH_CONCRETE_WEB -> R.string.joist_arrangement_single_85_with_concrete_web
    else -> 0
}