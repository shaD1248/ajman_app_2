package ajman.shayan.joisty.services

import ajman.shayan.joisty.models.report.tables.Table
import android.content.Context

class HtmlTableRenderer {
    fun getCssStyles() = """
        table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
            text-align: center;
            padding: 4px;
        }
    """

    fun renderTable(table: Table, context: Context): String {
        return """<table>
            <tr><th colspan="${table.columns.count()}">${translate(table.title, context)}</th></tr>
            <tr>${
            table.columns.joinToString(separator = "") { column ->
                "<th>${translate(table.columnTitles.getValue(column), context)}</th>"
            }
        }</tr>
            ${
            table.rows.joinToString(separator = "") { row ->
                """<tr>${
                    table.columns.joinToString(separator = "") { column ->
                        "<td>${translate(row.getValue(column), context)}</td>"
                    }
                }</tr>"""
            }
        }
        </table>"""
    }
}