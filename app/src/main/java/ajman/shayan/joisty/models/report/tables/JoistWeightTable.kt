package ajman.shayan.joisty.models.report.tables

import ajman.shayan.joisty.R
import ajman.shayan.joisty.enums.Unit
import ajman.shayan.joisty.models.datasets.DataSet
import ajman.shayan.joisty.models.structure.gamma_s
import ajman.shayan.joisty.services.formatQuantityValue
import kotlin.math.cos

class JoistWeightTable(dataSet: DataSet) : Table() {
    override var columns = listOf(COMPONENT, LENGTH, AREA_SECTION, VOLUME, WEIGHT)

    init {
        columnTitles = mapOf(
            COMPONENT to R.string.report_table_column_component,
            LENGTH to R.string.report_table_column_length,
            AREA_SECTION to R.string.report_table_column_area_section,
            VOLUME to R.string.report_table_column_volume,
            WEIGHT to R.string.report_table_column_weight,
        )
        title = R.string.report_table_joist_weight
        rows.addAll(
            mutableListOf(
                mapOf(
                    COMPONENT to R.string.report_table_component_bot_chord,
                    LENGTH to formatQuantityValue(dataSet.L.value, Unit.M),
                    AREA_SECTION to formatQuantityValue(dataSet.Asb.value, Unit.CM2),
                    VOLUME to formatQuantityValue(dataSet.Mj.Vsb, Unit.CM3),
                    WEIGHT to formatQuantityValue(gamma_s * dataSet.Mj.Vsb, Unit.KGF),
                ),
                mapOf(
                    COMPONENT to R.string.report_table_component_top_chord,
                    LENGTH to formatQuantityValue(dataSet.L.value, Unit.M),
                    AREA_SECTION to formatQuantityValue(dataSet.Ast.value, Unit.CM2),
                    VOLUME to formatQuantityValue(dataSet.Mj.Vst, Unit.CM3),
                    WEIGHT to formatQuantityValue(gamma_s * dataSet.Mj.Vst, Unit.KGF),
                ),
                mapOf(
                    COMPONENT to R.string.report_table_component_web_rebar,
                    LENGTH to formatQuantityValue(
                        dataSet.L / cos(dataSet.alpha.value ?: 0.0),
                        Unit.M
                    ),
                    AREA_SECTION to formatQuantityValue(dataSet.Asw.value, Unit.CM2),
                    VOLUME to formatQuantityValue(dataSet.Mj.Vsw, Unit.CM3),
                    WEIGHT to formatQuantityValue(gamma_s * dataSet.Mj.Vsw, Unit.KGF),
                ),
                mapOf(
                    COMPONENT to R.string.report_table_component_total,
                    LENGTH to "-",
                    AREA_SECTION to "-",
                    VOLUME to formatQuantityValue(dataSet.Mj / gamma_s, Unit.KGF),
                    WEIGHT to formatQuantityValue(dataSet.Mj.value, Unit.KGF),
                ),
            )
        )
    }
}