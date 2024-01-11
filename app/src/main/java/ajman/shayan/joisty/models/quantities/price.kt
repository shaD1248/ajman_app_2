package ajman.shayan.joisty.models.quantities

import ajman.shayan.joisty.models.datasets.DataSet
import ajman.shayan.joisty.models.quantity_models.EvaluatableQuantity
import ajman.shayan.joisty.models.quantity_models.div
import ajman.shayan.joisty.models.structure.gamma_s
import ajman.shayan.joisty.models.structure.kgf
import ajman.shayan.joisty.models.structure.m3
import ajman.shayan.joisty.models.templates.Assignment
import android.os.Build
import androidx.annotation.RequiresApi
import kotlin.math.pow

class price(dataSet: DataSet): EvaluatableQuantity(dataSet) {
    override val name = "price"
    override val dependencies = mutableSetOf("L", "Mj", "b", "d", "h", "wcD")

    var plate_weight_per_area: Double = 0.0
    var rebar_weight_per_area: Double = 0.0
    var angle_weight_per_area: Double = 0.0
    var concrete_volume_per_area: Double = 0.0
    var block_number_per_area: Double = 0.0

    var plate_price: Double = 0.0
    var rebar_price: Double = 0.0
    var angle_price: Double = 0.0
    var concrete_price: Double = 0.0
    var block_price: Double = 0.0

    @RequiresApi(Build.VERSION_CODES.O)
    override fun evaluate(): Triple<Double, MutableList<Assignment>, MutableSet<String>> {
        plate_weight_per_area = gamma_s * dataSet.Mj.Vsb / dataSet.b / dataSet.L
        rebar_weight_per_area = gamma_s * dataSet.Mj.Vsw / dataSet.b / dataSet.L
        angle_weight_per_area = gamma_s * dataSet.Mj.Vst / dataSet.b / dataSet.L
        concrete_volume_per_area = (dataSet.h.value ?: 0.0) +
                dataSet.wcD.beta_1 * (dataSet.d - dataSet.h) +
                (1 - dataSet.wcD.beta_1) * dataSet.wcD.beta_2 * (dataSet.d - dataSet.h) +
                (1 - dataSet.wcD.beta_2) * dataSet.wcD.et.pow(2) / dataSet.b
        block_number_per_area = 0.0

        plate_price = (dataSet.priceList?.plate ?: 0.0) / kgf * plate_weight_per_area
        rebar_price = (dataSet.priceList?.rebar ?: 0.0) / kgf * rebar_weight_per_area
        angle_price = (dataSet.priceList?.angle ?: 0.0) / kgf * angle_weight_per_area
        concrete_price = (dataSet.priceList?.concrete ?: 0.0) / m3 * concrete_volume_per_area
        block_price = (dataSet.priceList?.block ?: 0.0) * block_number_per_area

        val price = plate_price + rebar_price + angle_price + concrete_price + block_price
        actualDependencies = mutableSetOf(
            dataSet.L, dataSet.Mj, dataSet.b, dataSet.d, dataSet.h, dataSet.wcD,
        )
//        this.assignments = mutableListOf(Assignment("w_{cD}", price, Unit.KGF_OVER_M2))
        value = price
        return Triple(price, this.assignments, mutableSetOf())
    }
}