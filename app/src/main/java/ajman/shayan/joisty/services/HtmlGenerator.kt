package ajman.shayan.joisty.services

import ajman.shayan.joisty.entities.JoistDesign
import ajman.shayan.joisty.models.shapes.CanvasDataModel
import ajman.shayan.joisty.models.shapes.Point
import ajman.shayan.joisty.models.shapes.Rectangle
import ajman.shayan.joisty.models.structure.SteelSectionDetails
import ajman.shayan.joisty.models.structure.cm
import android.os.Build
import androidx.annotation.RequiresApi
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@RequiresApi(Build.VERSION_CODES.O)
class HtmlGenerator {
    fun generateHtml(joistDesign: JoistDesign, latexLines: MutableList<String>): String {
        val latexDocument = "$$\\begin{array}{l}" + latexLines.joinToString("\\\\") { latexLine ->
            "\\displaystyle $latexLine"
        } + "\\end{array}$$"
        val canvasJson = getCanvasJson(joistDesign)
        val html = """<!DOCTYPE html>
            <html lang="en">
              <head>
                <script type="text/x-mathjax-config">MathJax.Hub.Config({displayAlign:"left"});</script>
                <script type="text/javascript" async src="file:///android_asset/mathjax/Mathjax.js?config=TeX-AMS_CHTML"></script>
                <script type="text/javascript" async src="file:///android_asset/joisty/canvas.js"></script>
              </head>
              <body>
                <canvas id="joistCanvas" width="200" height="200" data='$canvasJson'></canvas>
                <div id="errorLog"></div>
                <div>$latexDocument</div>
              </body>
            </html>
        """
        return html.trimIndent()
    }

    private fun getCanvasJson(
        joistDesign: JoistDesign,
        W: Double = 200.0,
        H: Double = 200.0
    ): String {
        val dj = joistDesign.dj
        val t1 = joistDesign.steelSectionDetails.get_t1()
        val D = 1.2 * cm
        val bottomChord = when (joistDesign.steelSectionDetails) {
            SteelSectionDetails.P_120_3 -> CanvasDataModel(Rectangle(-6 * cm, 0.0, 12 * cm, t1))
            SteelSectionDetails.P_120_4 -> CanvasDataModel(Rectangle(-6 * cm, 0.0, 12 * cm, 0.4 * cm))
            SteelSectionDetails.P_120_3_P_3_120 -> CanvasDataModel(
                listOf(
                    Rectangle(-6 * cm, 0.0, 12 * cm, t1),
                    Rectangle(-D / 2, t1, -0.3 * cm, 12 * cm),
                )
            )
        }
        val web = CanvasDataModel(Rectangle(-D / 2, t1, D, dj - t1 - 0.3 * cm))
        val topChord = CanvasDataModel(
            listOf(
                Point(0.0, -0.3 * cm),
                Point(-2.4 * cm, -0.3 * cm),
                Point(-2.7 * cm, 0.0),
                Point(0.3 * cm, 0.0),
                Point(0.3 * cm, -3 * cm),
                Point(0.0, -2.7 * cm),
            )
        ).moveTo(D / 2, dj)
        val canvasDataModel = CanvasDataModel(listOf(bottomChord, web, topChord))
            .mirrorY().resizeBy(0.8 * H / dj).moveTo(W / 2, 0.9 * H)
        return Json.encodeToString(canvasDataModel)
    }
}