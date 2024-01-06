package ajman.shayan.joisty.models.requirements.limit_states

import ajman.shayan.joisty.R
import ajman.shayan.joisty.models.Requirement
import ajman.shayan.joisty.models.datasets.DataSet
import ajman.shayan.joisty.models.report.tables.StructureDeadLoadTable
import ajman.shayan.joisty.models.structure.LocatedCompositeSection

class StructureDeadLoad: Requirement<LocatedCompositeSection>() {
    override val mappedQuantity = "wcD"
    override val titleResourceId = R.string.report_section_structural_dead_load

    override fun updateTableValues(dataSet: DataSet) {
        tables.add(StructureDeadLoadTable(dataSet))
    }
}