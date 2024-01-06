package ajman.shayan.joisty.models.report.tables

import ajman.shayan.joisty.R
import ajman.shayan.joisty.models.structure.Occupancy

const val COL_COMPONENT = "COMPONENT"
const val COL_SPECIFIC_WEIGHT = "SPECIFIC_WEIGHT"
const val COL_THICKNESS = "THICKNESS"
const val COL_AREA_LOAD = "AREA_LOAD"

class OccupancyDeadLoadTable(occupancy: Occupancy) : Table() {
    override var columns = listOf(COL_COMPONENT, COL_SPECIFIC_WEIGHT, COL_THICKNESS, COL_AREA_LOAD)

    init {
        columnTitles = mapOf(
            COL_COMPONENT to R.string.report_table_dead_load_column_component,
            COL_SPECIFIC_WEIGHT to R.string.report_table_dead_load_column_specific_weight,
            COL_THICKNESS to R.string.report_table_dead_load_column_thickness,
            COL_AREA_LOAD to R.string.report_table_dead_load_column_area_load,
        )
        when (occupancy) {
            Occupancy.COMMERCIAL -> {
                title = R.string.report_table_dead_load_commercial_title
                rows.addAll(
                    mutableListOf(
                        mapOf(
                            COL_COMPONENT to R.string.report_table_dead_load_component_mosaic_stone,
                            COL_SPECIFIC_WEIGHT to "2400",
                            COL_THICKNESS to "3",
                            COL_AREA_LOAD to "72",
                        ),
                        mapOf(
                            COL_COMPONENT to R.string.report_table_dead_load_component_cement_and_sand_mortar,
                            COL_SPECIFIC_WEIGHT to "2100",
                            COL_THICKNESS to "2",
                            COL_AREA_LOAD to "42",
                        ),
                        mapOf(
                            COL_COMPONENT to R.string.report_table_dead_load_component_concrete_with_mineral_pumice_and_sand,
                            COL_SPECIFIC_WEIGHT to "1300",
                            COL_THICKNESS to "7",
                            COL_AREA_LOAD to "91",
                        ),
                        mapOf(
                            COL_COMPONENT to R.string.report_table_dead_load_component_plaster_and_soil_mortar,
                            COL_SPECIFIC_WEIGHT to "1600",
                            COL_THICKNESS to "2",
                            COL_AREA_LOAD to "32",
                        ),
                        mapOf(
                            COL_COMPONENT to R.string.report_table_dead_load_component_plaster_mortar,
                            COL_SPECIFIC_WEIGHT to "1300",
                            COL_THICKNESS to "1",
                            COL_AREA_LOAD to "13",
                        ),
                        mapOf(
                            COL_COMPONENT to R.string.report_table_dead_load_component_total,
                            COL_SPECIFIC_WEIGHT to "-",
                            COL_THICKNESS to "-",
                            COL_AREA_LOAD to "250",
                        )
                    )
                )
            }
            Occupancy.PARKING -> {
                title = R.string.report_table_dead_load_parking_title
                rows.addAll(
                    mutableListOf(
                        mapOf(
                            COL_COMPONENT to R.string.report_table_dead_load_component_mosaic_stone,
                            COL_SPECIFIC_WEIGHT to "2400",
                            COL_THICKNESS to "3",
                            COL_AREA_LOAD to "72",
                        ),
                        mapOf(
                            COL_COMPONENT to R.string.report_table_dead_load_component_cement_and_sand_mortar,
                            COL_SPECIFIC_WEIGHT to "2100",
                            COL_THICKNESS to "2",
                            COL_AREA_LOAD to "42",
                        ),
                        mapOf(
                            COL_COMPONENT to R.string.report_table_dead_load_component_concrete_with_mineral_pumice_and_sand,
                            COL_SPECIFIC_WEIGHT to "1300",
                            COL_THICKNESS to "7",
                            COL_AREA_LOAD to "91",
                        ),
                        mapOf(
                            COL_COMPONENT to R.string.report_table_dead_load_component_plaster_and_soil_mortar,
                            COL_SPECIFIC_WEIGHT to "1600",
                            COL_THICKNESS to "2",
                            COL_AREA_LOAD to "32",
                        ),
                        mapOf(
                            COL_COMPONENT to R.string.report_table_dead_load_component_plaster_mortar,
                            COL_SPECIFIC_WEIGHT to "1300",
                            COL_THICKNESS to "1",
                            COL_AREA_LOAD to "13",
                        ),
                        mapOf(
                            COL_COMPONENT to R.string.report_table_dead_load_component_total,
                            COL_SPECIFIC_WEIGHT to "-",
                            COL_THICKNESS to "-",
                            COL_AREA_LOAD to "250",
                        )
                    )
                )
            }
            Occupancy.RESIDENTIAL -> {
                title = R.string.report_table_dead_load_residential_title
                rows.addAll(
                    mutableListOf(
                        mapOf(
                            COL_COMPONENT to R.string.report_table_dead_load_component_mosaic_stone,
                            COL_SPECIFIC_WEIGHT to "2400",
                            COL_THICKNESS to "2",
                            COL_AREA_LOAD to "48",
                        ),
                        mapOf(
                            COL_COMPONENT to R.string.report_table_dead_load_component_cement_and_sand_mortar,
                            COL_SPECIFIC_WEIGHT to "2100",
                            COL_THICKNESS to "2",
                            COL_AREA_LOAD to "42",
                        ),
                        mapOf(
                            COL_COMPONENT to R.string.report_table_dead_load_component_concrete_with_mineral_pumice_and_sand,
                            COL_SPECIFIC_WEIGHT to "1300",
                            COL_THICKNESS to "5",
                            COL_AREA_LOAD to "65",
                        ),
                        mapOf(
                            COL_COMPONENT to R.string.report_table_dead_load_component_plaster_and_soil_mortar,
                            COL_SPECIFIC_WEIGHT to "1600",
                            COL_THICKNESS to "2",
                            COL_AREA_LOAD to "32",
                        ),
                        mapOf(
                            COL_COMPONENT to R.string.report_table_dead_load_component_plaster_mortar,
                            COL_SPECIFIC_WEIGHT to "1300",
                            COL_THICKNESS to "1",
                            COL_AREA_LOAD to "13",
                        ),
                        mapOf(
                            COL_COMPONENT to R.string.report_table_dead_load_component_total,
                            COL_SPECIFIC_WEIGHT to "-",
                            COL_THICKNESS to "-",
                            COL_AREA_LOAD to "200",
                        )
                    )
                )
            }
            Occupancy.ROOF -> {
                title = R.string.report_table_dead_load_roof_title
                rows.addAll(
                    mutableListOf(
                        mapOf(
                            COL_COMPONENT to R.string.report_table_dead_load_component_cement_mosaic,
                            COL_SPECIFIC_WEIGHT to "2250",
                            COL_THICKNESS to "3",
                            COL_AREA_LOAD to "68",
                        ),
                        mapOf(
                            COL_COMPONENT to R.string.report_table_dead_load_component_two_later_bitumen_coated_bag,
                            COL_SPECIFIC_WEIGHT to "-",
                            COL_THICKNESS to "-",
                            COL_AREA_LOAD to "15",
                        ),
                        mapOf(
                            COL_COMPONENT to R.string.report_table_dead_load_component_cement_and_sand_mortar,
                            COL_SPECIFIC_WEIGHT to "2100",
                            COL_THICKNESS to "2",
                            COL_AREA_LOAD to "42",
                        ),
                        mapOf(
                            COL_COMPONENT to R.string.report_table_dead_load_component_concrete_with_mineral_pumice_and_sand,
                            COL_SPECIFIC_WEIGHT to "1300",
                            COL_THICKNESS to "10",
                            COL_AREA_LOAD to "130",
                        ),
                        mapOf(
                            COL_COMPONENT to R.string.report_table_dead_load_component_plaster_and_soil_mortar,
                            COL_SPECIFIC_WEIGHT to "1600",
                            COL_THICKNESS to "2",
                            COL_AREA_LOAD to "32",
                        ),
                        mapOf(
                            COL_COMPONENT to R.string.report_table_dead_load_component_plaster_mortar,
                            COL_SPECIFIC_WEIGHT to "1300",
                            COL_THICKNESS to "1",
                            COL_AREA_LOAD to "13",
                        ),
                        mapOf(
                            COL_COMPONENT to R.string.report_table_dead_load_component_total,
                            COL_SPECIFIC_WEIGHT to "-",
                            COL_THICKNESS to "-",
                            COL_AREA_LOAD to "300",
                        )
                    )
                )
            }
        }
    }
}