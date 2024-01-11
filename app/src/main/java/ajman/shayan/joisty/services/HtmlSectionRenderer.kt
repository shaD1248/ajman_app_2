package ajman.shayan.joisty.services

import ajman.shayan.joisty.models.report.ReportSection
import android.content.Context

class HtmlSectionRenderer {
    fun getScriptTags() = """
        ${HtmlLatexRenderer().getScriptTags()}
        <script>
            function toggleSection(sectionId) {
                var content = document.getElementById(sectionId);
                if (content.style.display === 'none' || content.style.display === '') {
                    content.style.display = 'block';
                } else {
                    content.style.display = 'none';
                }
            }
        </script>
    """

    fun getCssStyles() = """
        ${HtmlTableRenderer().getCssStyles()}
        h3:hover {
            background-color: #f0f0f0;
        }
        .sectionContent {
            display: none;
        }
    """

    fun render(reportSection: ReportSection, context: Context): String {
        val latexRenderer = HtmlLatexRenderer()
        val htmlTableRenderer = HtmlTableRenderer()
        val sectionId = "section_${reportSection.titleResourceId}"
        val title = translate(reportSection.titleResourceId, context)
        val latexParagraphs = reportSection.paragraphs.joinToString(separator = "") {
            "<p>${it.text}</p>${latexRenderer.getBodyTags(it.latexLines)}"
        }
        val tables = reportSection.tables.joinToString(separator = "<p>&nbsp;</p>") { table ->
            htmlTableRenderer.renderTable(table, context)
        }
        return """
            <h3 onclick="toggleSection('$sectionId')">$title</h3>
            <div id="$sectionId" class="sectionContent">$tables$latexParagraphs</div>
        """
    }
}