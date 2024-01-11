package ajman.shayan.joisty.models.report.tables

import ajman.shayan.joisty.R
import ajman.shayan.joisty.enums.Unit
import ajman.shayan.joisty.models.datasets.DataSet
import ajman.shayan.joisty.models.quantity_models.div
import ajman.shayan.joisty.models.structure.gamma_b
import ajman.shayan.joisty.models.structure.gamma_c
import ajman.shayan.joisty.models.structure.gamma_rc
import ajman.shayan.joisty.services.formatQuantityValue

class StructureDeadLoadTable(dataSet: DataSet) : Table() {
    override var columns = listOf(COMPONENT, SPECIFIC_WEIGHT, LENGTH_RATIO, WIDTH_RATIO, HEIGHT, AREA_LOAD)

    init {
        columnTitles = mapOf(
            COMPONENT to R.string.report_table_column_component,
            SPECIFIC_WEIGHT to R.string.report_table_column_specific_weight,
            LENGTH_RATIO to R.string.report_table_column_length_ratio,
            WIDTH_RATIO to R.string.report_table_column_width_ratio,
            HEIGHT to R.string.report_table_column_height,
            AREA_LOAD to R.string.report_table_column_area_load,
        )
        title = R.string.report_table_dead_load_structural_ceiling
        rows.addAll(
            mutableListOf(
                mapOf(
                    COMPONENT to R.string.report_table_component_joist,
                    SPECIFIC_WEIGHT to "-",
                    LENGTH_RATIO to "-",
                    WIDTH_RATIO to "-",
                    HEIGHT to "-",
                    AREA_LOAD to formatQuantityValue(dataSet.wcD.sigma_j, Unit.KGF_OVER_M2),
                ),
                mapOf(
                    COMPONENT to R.string.report_table_component_concrete_slab,
                    SPECIFIC_WEIGHT to formatQuantityValue(gamma_rc, Unit.KGF_OVER_M3),
                    LENGTH_RATIO to "1",
                    WIDTH_RATIO to "1",
                    HEIGHT to formatQuantityValue(dataSet.h.value, Unit.CM),
                    AREA_LOAD to formatQuantityValue(dataSet.wcD.sigma_s, Unit.KGF_OVER_M2),
                ),
                mapOf(
                    COMPONENT to R.string.report_table_component_concrete_web,
                    SPECIFIC_WEIGHT to formatQuantityValue(gamma_c, Unit.KGF_OVER_M3),
                    LENGTH_RATIO to "1",
                    WIDTH_RATIO to formatQuantityValue(dataSet.wcD.beta_1, Unit.UNIT),
                    HEIGHT to formatQuantityValue(dataSet.d - dataSet.h, Unit.CM),
                    AREA_LOAD to formatQuantityValue(dataSet.wcD.sigma_w, Unit.KGF_OVER_M2),
                ),
                mapOf(
                    COMPONENT to R.string.report_table_component_tie,
                    SPECIFIC_WEIGHT to formatQuantityValue(gamma_rc, Unit.KGF_OVER_M3),
                    LENGTH_RATIO to formatQuantityValue(dataSet.wcD.beta_2, Unit.UNIT),
                    WIDTH_RATIO to formatQuantityValue(1 - dataSet.wcD.beta_1, Unit.UNIT),
                    HEIGHT to formatQuantityValue(dataSet.d - dataSet.h, Unit.CM),
                    AREA_LOAD to formatQuantityValue(dataSet.wcD.sigma_t, Unit.KGF_OVER_M2),
                ),
                mapOf(
                    COMPONENT to R.string.report_table_component_triangular_cut,
                    SPECIFIC_WEIGHT to formatQuantityValue(gamma_c - gamma_b, Unit.KGF_OVER_M3),
                    LENGTH_RATIO to formatQuantityValue(1 - dataSet.wcD.beta_2, Unit.UNIT),
                    WIDTH_RATIO to formatQuantityValue(dataSet.wcD.et / dataSet.b, Unit.UNIT),
                    HEIGHT to formatQuantityValue(dataSet.wcD.et, Unit.CM),
                    AREA_LOAD to formatQuantityValue(dataSet.wcD.sigma_c, Unit.KGF_OVER_M2),
                ),
                mapOf(
                    COMPONENT to R.string.report_table_component_block,
                    SPECIFIC_WEIGHT to formatQuantityValue(gamma_b, Unit.KGF_OVER_M3),
                    LENGTH_RATIO to formatQuantityValue(1 - dataSet.wcD.beta_2, Unit.UNIT),
                    WIDTH_RATIO to formatQuantityValue(1 - dataSet.wcD.beta_1, Unit.UNIT),
                    HEIGHT to formatQuantityValue(dataSet.d - dataSet.h + dataSet.wcD.hs, Unit.CM),
                    AREA_LOAD to formatQuantityValue(dataSet.wcD.sigma_b, Unit.KGF_OVER_M2),
                ),
                mapOf(
                    COMPONENT to R.string.report_table_component_total,
                    SPECIFIC_WEIGHT to "-",
                    LENGTH_RATIO to "-",
                    WIDTH_RATIO to "-",
                    HEIGHT to "-",
                    AREA_LOAD to formatQuantityValue(dataSet.wcD.sigma, Unit.KGF_OVER_M2),
                ),
            )
        )
    }
}