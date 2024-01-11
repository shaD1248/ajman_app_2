package ajman.shayan.joisty.models.requirements.limit_states

import ajman.shayan.joisty.R
import ajman.shayan.joisty.models.Requirement
import ajman.shayan.joisty.models.datasets.DataSet
import ajman.shayan.joisty.models.report.tables.JoistWeightTable
import ajman.shayan.joisty.models.structure.LocatedCompositeSection

class JoistWeight: Requirement<LocatedCompositeSection>() {
    override val mappedQuantity = "Mj"
    override val titleResourceId = R.string.report_section_joist_weight

    override fun updateTableValues(dataSet: DataSet) {
        tables.add(JoistWeightTable(dataSet))
    }
}