package ajman.shayan.joisty.services

class HtmlLatexRenderer {
    fun getScriptTags(): String = """
        <script type="text/x-mathjax-config">MathJax.Hub.Config({displayAlign:"left"});</script>
        <script type="text/javascript" async src="file:///android_asset/mathjax/Mathjax.js?config=TeX-AMS_CHTML"></script>
        """
    fun getBodyTags(latexLines: List<String>): String =
        "<div style=\"direction: ltr; overflow: auto; white-space: nowrap;\">$$\\begin{array}{l}" + latexLines.joinToString("\\\\") { "\\displaystyle $it" } + "\\end{array}$$</div>"
}