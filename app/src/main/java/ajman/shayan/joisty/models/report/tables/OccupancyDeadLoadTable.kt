package ajman.shayan.joisty.models.report.tables

import ajman.shayan.joisty.R
import ajman.shayan.joisty.models.structure.Occupancy

class OccupancyDeadLoadTable(occupancy: Occupancy) : Table() {
    override var columns = listOf(COMPONENT, SPECIFIC_WEIGHT, THICKNESS, AREA_LOAD)

    init {
        columnTitles = mapOf(
            COMPONENT to R.string.report_table_column_component,
            SPECIFIC_WEIGHT to R.string.report_table_column_specific_weight,
            THICKNESS to R.string.report_table_column_thickness,
            AREA_LOAD to R.string.report_table_column_area_load,
        )
        when (occupancy) {
            Occupancy.COMMERCIAL -> {
                title = R.string.report_table_dead_load_commercial
                rows.addAll(
                    mutableListOf(
                        mapOf(
                            COMPONENT to R.string.report_table_component_mosaic_stone,
                            SPECIFIC_WEIGHT to "2400",
                            THICKNESS to "3",
                            AREA_LOAD to "72",
                        ),
                        mapOf(
                            COMPONENT to R.string.report_table_component_cement_and_sand_mortar,
                            SPECIFIC_WEIGHT to "2100",
                            THICKNESS to "2",
                            AREA_LOAD to "42",
                        ),
                        mapOf(
                            COMPONENT to R.string.report_table_component_concrete_with_mineral_pumice_and_sand,
                            SPECIFIC_WEIGHT to "1300",
                            THICKNESS to "7",
                            AREA_LOAD to "91",
                        ),
                        mapOf(
                            COMPONENT to R.string.report_table_component_plaster_and_soil_mortar,
                            SPECIFIC_WEIGHT to "1600",
                            THICKNESS to "2",
                            AREA_LOAD to "32",
                        ),
                        mapOf(
                            COMPONENT to R.string.report_table_component_plaster_mortar,
                            SPECIFIC_WEIGHT to "1300",
                            THICKNESS to "1",
                            AREA_LOAD to "13",
                        ),
                        mapOf(
                            COMPONENT to R.string.report_table_component_total,
                            SPECIFIC_WEIGHT to "-",
                            THICKNESS to "-",
                            AREA_LOAD to "250",
                        )
                    )
                )
            }
            Occupancy.PARKING -> {
                title = R.string.report_table_dead_load_parking
                rows.addAll(
                    mutableListOf(
                        mapOf(
                            COMPONENT to R.string.report_table_component_mosaic_stone,
                            SPECIFIC_WEIGHT to "2400",
                            THICKNESS to "3",
                            AREA_LOAD to "72",
                        ),
                        mapOf(
                            COMPONENT to R.string.report_table_component_cement_and_sand_mortar,
                            SPECIFIC_WEIGHT to "2100",
                            THICKNESS to "2",
                            AREA_LOAD to "42",
                        ),
                        mapOf(
                            COMPONENT to R.string.report_table_component_concrete_with_mineral_pumice_and_sand,
                            SPECIFIC_WEIGHT to "1300",
                            THICKNESS to "7",
                            AREA_LOAD to "91",
                        ),
                        mapOf(
                            COMPONENT to R.string.report_table_component_plaster_and_soil_mortar,
                            SPECIFIC_WEIGHT to "1600",
                            THICKNESS to "2",
                            AREA_LOAD to "32",
                        ),
                        mapOf(
                            COMPONENT to R.string.report_table_component_plaster_mortar,
                            SPECIFIC_WEIGHT to "1300",
                            THICKNESS to "1",
                            AREA_LOAD to "13",
                        ),
                        mapOf(
                            COMPONENT to R.string.report_table_component_total,
                            SPECIFIC_WEIGHT to "-",
                            THICKNESS to "-",
                            AREA_LOAD to "250",
                        )
                    )
                )
            }
            Occupancy.RESIDENTIAL -> {
                title = R.string.report_table_dead_load_residential
                rows.addAll(
                    mutableListOf(
                        mapOf(
                            COMPONENT to R.string.report_table_component_mosaic_stone,
                            SPECIFIC_WEIGHT to "2400",
                            THICKNESS to "2",
                            AREA_LOAD to "48",
                        ),
                        mapOf(
                            COMPONENT to R.string.report_table_component_cement_and_sand_mortar,
                            SPECIFIC_WEIGHT to "2100",
                            THICKNESS to "2",
                            AREA_LOAD to "42",
                        ),
                        mapOf(
                            COMPONENT to R.string.report_table_component_concrete_with_mineral_pumice_and_sand,
                            SPECIFIC_WEIGHT to "1300",
                            THICKNESS to "5",
                            AREA_LOAD to "65",
                        ),
                        mapOf(
                            COMPONENT to R.string.report_table_component_plaster_and_soil_mortar,
                            SPECIFIC_WEIGHT to "1600",
                            THICKNESS to "2",
                            AREA_LOAD to "32",
                        ),
                        mapOf(
                            COMPONENT to R.string.report_table_component_plaster_mortar,
                            SPECIFIC_WEIGHT to "1300",
                            THICKNESS to "1",
                            AREA_LOAD to "13",
                        ),
                        mapOf(
                            COMPONENT to R.string.report_table_component_total,
                            SPECIFIC_WEIGHT to "-",
                            THICKNESS to "-",
                            AREA_LOAD to "200",
                        )
                    )
                )
            }
            Occupancy.ROOF -> {
                title = R.string.report_table_dead_load_roof
                rows.addAll(
                    mutableListOf(
                        mapOf(
                            COMPONENT to R.string.report_table_component_cement_mosaic,
                            SPECIFIC_WEIGHT to "2250",
                            THICKNESS to "3",
                            AREA_LOAD to "68",
                        ),
                        mapOf(
                            COMPONENT to R.string.report_table_component_two_later_bitumen_coated_bag,
                            SPECIFIC_WEIGHT to "-",
                            THICKNESS to "-",
                            AREA_LOAD to "15",
                        ),
                        mapOf(
                            COMPONENT to R.string.report_table_component_cement_and_sand_mortar,
                            SPECIFIC_WEIGHT to "2100",
                            THICKNESS to "2",
                            AREA_LOAD to "42",
                        ),
                        mapOf(
                            COMPONENT to R.string.report_table_component_concrete_with_mineral_pumice_and_sand,
                            SPECIFIC_WEIGHT to "1300",
                            THICKNESS to "10",
                            AREA_LOAD to "130",
                        ),
                        mapOf(
                            COMPONENT to R.string.report_table_component_plaster_and_soil_mortar,
                            SPECIFIC_WEIGHT to "1600",
                            THICKNESS to "2",
                            AREA_LOAD to "32",
                        ),
                        mapOf(
                            COMPONENT to R.string.report_table_component_plaster_mortar,
                            SPECIFIC_WEIGHT to "1300",
                            THICKNESS to "1",
                            AREA_LOAD to "13",
                        ),
                        mapOf(
                            COMPONENT to R.string.report_table_component_total,
                            SPECIFIC_WEIGHT to "-",
                            THICKNESS to "-",
                            AREA_LOAD to "300",
                        )
                    )
                )
            }
        }
    }
}