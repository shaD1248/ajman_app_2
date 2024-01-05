package ajman.shayan.joisty.models.requirements.limit_states

import ajman.shayan.joisty.R
import ajman.shayan.joisty.models.Requirement
import ajman.shayan.joisty.models.datasets.DataSet
import ajman.shayan.joisty.models.report.tables.DeadLoadTable
import ajman.shayan.joisty.models.structure.LocatedCompositeSection

class OccupancyLoading: Requirement<LocatedCompositeSection>() {
    override val mappedQuantity = "wu"
    override val titleResourceId = R.string.report_section_occupancy_loading

    override fun updateTableValues(dataSet: DataSet) {
        tables.add(DeadLoadTable(dataSet.occupancy))
    }
}