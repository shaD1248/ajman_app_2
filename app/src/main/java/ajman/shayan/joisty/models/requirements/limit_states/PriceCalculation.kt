package ajman.shayan.joisty.models.requirements.limit_states

import ajman.shayan.joisty.R
import ajman.shayan.joisty.models.Requirement
import ajman.shayan.joisty.models.datasets.DataSet
import ajman.shayan.joisty.models.report.tables.PriceTable
import ajman.shayan.joisty.models.structure.LocatedCompositeSection
import android.os.Build
import androidx.annotation.RequiresApi

class PriceCalculation: Requirement<LocatedCompositeSection>() {
    override val mappedQuantity = "price"
    override val titleResourceId = R.string.report_section_price_calculation

    @RequiresApi(Build.VERSION_CODES.O)
    override fun updateTableValues(dataSet: DataSet) {
        tables.add(PriceTable(dataSet))
    }
}