package ajman.shayan.joisty.models.report.tables

abstract class Table {
    abstract var columns: List<String>
    var title: Int = 0
    var columnTitles = mapOf<String, Int>()
    val rows = mutableListOf<Map<String, Any>>()
}