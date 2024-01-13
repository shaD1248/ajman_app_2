package ajman.shayan.joisty.models.report.tables

import ajman.shayan.joisty.R
import ajman.shayan.joisty.enums.Unit
import ajman.shayan.joisty.models.datasets.DataSet
import ajman.shayan.joisty.services.formatQuantityValue
import android.os.Build
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.O)
class PriceTable(dataSet: DataSet) : Table() {
    override var columns = listOf(COMPONENT, AMOUNT, UNIT, PRICE_PER_UNIT, PRICE)

    init {
        columnTitles = mapOf(
            COMPONENT to R.string.report_table_column_component,
            AMOUNT to R.string.report_table_column_amount,
            UNIT to R.string.report_table_column_unit,
            PRICE_PER_UNIT to R.string.report_table_column_price_per_unit,
            PRICE to R.string.report_table_column_price,
        )
        title = R.string.report_table_price_breakdown
        rows.addAll(
            mutableListOf(
                mapOf(
                    COMPONENT to R.string.label_plate,
                    AMOUNT to formatQuantityValue(dataSet.price.plate_weight_per_area, Unit.KGF_OVER_M2),
                    UNIT to Unit.KGF_OVER_M2,
                    PRICE_PER_UNIT to formatQuantityValue(dataSet.priceList?.plate ?: 0.0, Unit.UNIT),
                    PRICE to formatQuantityValue(dataSet.price.plate_price, Unit.UNIT_OVER_M2),
                ),
                mapOf(
                    COMPONENT to R.string.label_rebar,
                    AMOUNT to formatQuantityValue(dataSet.price.rebar_weight_per_area, Unit.KGF_OVER_M2),
                    UNIT to Unit.KGF_OVER_M2,
                    PRICE_PER_UNIT to formatQuantityValue(dataSet.priceList?.rebar ?: 0.0, Unit.UNIT),
                    PRICE to formatQuantityValue(dataSet.price.rebar_price, Unit.UNIT_OVER_M2),
                ),
                mapOf(
                    COMPONENT to R.string.label_angle,
                    AMOUNT to formatQuantityValue(dataSet.price.angle_weight_per_area, Unit.KGF_OVER_M2),
                    UNIT to Unit.KGF_OVER_M2,
                    PRICE_PER_UNIT to formatQuantityValue(dataSet.priceList?.angle ?: 0.0, Unit.UNIT),
                    PRICE to formatQuantityValue(dataSet.price.angle_price, Unit.UNIT_OVER_M2),
                ),
                mapOf(
                    COMPONENT to R.string.label_concrete,
                    AMOUNT to formatQuantityValue(dataSet.price.concrete_volume_per_area, Unit.CM),
                    UNIT to Unit.CM,
                    PRICE_PER_UNIT to formatQuantityValue(dataSet.priceList?.concrete ?: 0.0, Unit.UNIT),
                    PRICE to formatQuantityValue(dataSet.price.concrete_price, Unit.UNIT_OVER_M2),
                ),
                mapOf(
                    COMPONENT to R.string.label_block,
                    AMOUNT to formatQuantityValue(dataSet.price.block_number_per_area, Unit.UNIT_OVER_M2),
                    UNIT to Unit.UNIT_OVER_M2,
                    PRICE_PER_UNIT to formatQuantityValue(dataSet.priceList?.block ?: 0.0, Unit.UNIT),
                    PRICE to formatQuantityValue(dataSet.price.block_price, Unit.UNIT_OVER_M2),
                ),
                mapOf(
                    COMPONENT to R.string.report_table_component_total,
                    AMOUNT to "-",
                    UNIT to "-",
                    PRICE_PER_UNIT to "-",
                    PRICE to formatQuantityValue(dataSet.price.value ?: 0.0, Unit.UNIT_OVER_M2),
                ),
            )
        )
    }
}