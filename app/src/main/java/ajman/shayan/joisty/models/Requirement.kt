package ajman.shayan.joisty.models

import ajman.shayan.joisty.models.datasets.DataSet
import ajman.shayan.joisty.models.report.tables.Table

abstract class Requirement<type> {
    abstract val mappedQuantity: String
    abstract val titleResourceId: Int
    val tables = mutableListOf<Table>()
    open fun updateTableValues(dataSet: DataSet) {
    }
}