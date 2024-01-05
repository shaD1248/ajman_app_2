package ajman.shayan.joisty.models.report

import ajman.shayan.joisty.models.report.tables.Table

class ReportSection(var titleResourceId: Int, val paragraphs: List<Paragraph>, val tables: List<Table>)