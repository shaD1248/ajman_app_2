package ajman.shayan.joisty.models.report.tables

import ajman.shayan.joisty.R
import ajman.shayan.joisty.enums.Unit
import ajman.shayan.joisty.models.datasets.DataSet
import ajman.shayan.joisty.models.quantity_models.div
import ajman.shayan.joisty.models.structure.gamma_b
import ajman.shayan.joisty.models.structure.gamma_c
import ajman.shayan.joisty.models.structure.gamma_rc
import ajman.shayan.joisty.services.formatQuantityValue

//const val COL_COMPONENT = "COMPONENT"
//const val COL_SPECIFIC_WEIGHT = "SPECIFIC_WEIGHT"
const val COL_LENGTH_RATIO = "LENGTH_RATIO"
const val COL_WIDTH_RATIO = "WIDTH_RATIO"
const val COL_HEIGHT = "HEIGHT"
//const val COL_AREA_LOAD = "AREA_LOAD"

class StructureDeadLoadTable(dataSet: DataSet) : Table() {
    override var columns = listOf(COL_COMPONENT, COL_SPECIFIC_WEIGHT, COL_LENGTH_RATIO, COL_WIDTH_RATIO, COL_HEIGHT, COL_AREA_LOAD)

    init {
        columnTitles = mapOf(
            COL_COMPONENT to R.string.report_table_dead_load_column_component,
            COL_SPECIFIC_WEIGHT to R.string.report_table_dead_load_column_specific_weight,
            COL_LENGTH_RATIO to R.string.report_table_dead_load_column_length_ratio,
            COL_WIDTH_RATIO to R.string.report_table_dead_load_column_width_ratio,
            COL_HEIGHT to R.string.report_table_dead_load_column_height,
            COL_AREA_LOAD to R.string.report_table_dead_load_column_area_load,
        )
        title = R.string.report_table_dead_load_structural_ceiling_title
        rows.addAll(
            mutableListOf(
                mapOf(
                    COL_COMPONENT to R.string.report_table_dead_load_component_joist,
                    COL_SPECIFIC_WEIGHT to "-",
                    COL_LENGTH_RATIO to "-",
                    COL_WIDTH_RATIO to "-",
                    COL_HEIGHT to "-",
                    COL_AREA_LOAD to formatQuantityValue(dataSet.wcD.sigma_j, Unit.KGF_OVER_M2),
                ),
                mapOf(
                    COL_COMPONENT to R.string.report_table_dead_load_component_concrete_slab,
                    COL_SPECIFIC_WEIGHT to formatQuantityValue(gamma_rc, Unit.KGF_OVER_M3),
                    COL_LENGTH_RATIO to "1",
                    COL_WIDTH_RATIO to "1",
                    COL_HEIGHT to formatQuantityValue(dataSet.h.value, Unit.CM),
                    COL_AREA_LOAD to formatQuantityValue(dataSet.wcD.sigma_s, Unit.KGF_OVER_M2),
                ),
                mapOf(
                    COL_COMPONENT to R.string.report_table_dead_load_component_concrete_web,
                    COL_SPECIFIC_WEIGHT to formatQuantityValue(gamma_c, Unit.KGF_OVER_M3),
                    COL_LENGTH_RATIO to "1",
                    COL_WIDTH_RATIO to formatQuantityValue(dataSet.wcD.beta_1, Unit.UNIT),
                    COL_HEIGHT to formatQuantityValue(dataSet.d - dataSet.h, Unit.CM),
                    COL_AREA_LOAD to formatQuantityValue(dataSet.wcD.sigma_w, Unit.KGF_OVER_M2),
                ),
                mapOf(
                    COL_COMPONENT to R.string.report_table_dead_load_component_tie,
                    COL_SPECIFIC_WEIGHT to formatQuantityValue(gamma_rc, Unit.KGF_OVER_M3),
                    COL_LENGTH_RATIO to formatQuantityValue(dataSet.wcD.beta_2, Unit.UNIT),
                    COL_WIDTH_RATIO to formatQuantityValue(1 - dataSet.wcD.beta_1, Unit.UNIT),
                    COL_HEIGHT to formatQuantityValue(dataSet.d - dataSet.h, Unit.CM),
                    COL_AREA_LOAD to formatQuantityValue(dataSet.wcD.sigma_t, Unit.KGF_OVER_M2),
                ),
                mapOf(
                    COL_COMPONENT to R.string.report_table_dead_load_component_triangular_cut,
                    COL_SPECIFIC_WEIGHT to formatQuantityValue(gamma_c - gamma_b, Unit.KGF_OVER_M3),
                    COL_LENGTH_RATIO to formatQuantityValue(1 - dataSet.wcD.beta_2, Unit.UNIT),
                    COL_WIDTH_RATIO to formatQuantityValue(dataSet.wcD.et / dataSet.b, Unit.UNIT),
                    COL_HEIGHT to formatQuantityValue(dataSet.wcD.et, Unit.CM),
                    COL_AREA_LOAD to formatQuantityValue(dataSet.wcD.sigma_c, Unit.KGF_OVER_M2),
                ),
                mapOf(
                    COL_COMPONENT to R.string.report_table_dead_load_component_block,
                    COL_SPECIFIC_WEIGHT to formatQuantityValue(gamma_b, Unit.KGF_OVER_M3),
                    COL_LENGTH_RATIO to formatQuantityValue(1 - dataSet.wcD.beta_2, Unit.UNIT),
                    COL_WIDTH_RATIO to formatQuantityValue(1 - dataSet.wcD.beta_1, Unit.UNIT),
                    COL_HEIGHT to formatQuantityValue(dataSet.d - dataSet.h + dataSet.wcD.hs, Unit.CM),
                    COL_AREA_LOAD to formatQuantityValue(dataSet.wcD.sigma_b, Unit.KGF_OVER_M2),
                ),
                mapOf(
                    COL_COMPONENT to R.string.report_table_dead_load_component_total,
                    COL_SPECIFIC_WEIGHT to "-",
                    COL_LENGTH_RATIO to "-",
                    COL_WIDTH_RATIO to "-",
                    COL_HEIGHT to "-",
                    COL_AREA_LOAD to formatQuantityValue(dataSet.wcD.sigma, Unit.KGF_OVER_M2),
                ),
            )
        )
    }
}