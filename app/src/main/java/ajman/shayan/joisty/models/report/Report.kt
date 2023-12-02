package ajman.shayan.joisty.models.report

import ajman.shayan.joisty.entities.JoistDesign
import ajman.shayan.joisty.models.shapes.CanvasDataModel

class Report(val sections: List<ReportSection>, val joistDesign: JoistDesign, val canvasDataModel: CanvasDataModel? = null)