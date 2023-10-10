package ajman.shd.app1.models.requirements.limit_states.flexure

import ajman.shd.app1.models.QuantityEvaluator
import ajman.shd.app1.models.Requirement
import ajman.shd.app1.models.RequirementApplication
import ajman.shd.app1.models.joinMessages
import ajman.shd.app1.models.structure.LocatedCompositeSection

class FlexuralStrengthOfCompositeSection: Requirement<LocatedCompositeSection>() {
    override val mappedQuantity = "ratio_b"
    override fun apply(target: LocatedCompositeSection): RequirementApplication {
        val quantitiesToBeEvaluated = mutableSetOf("ratio_b")
        val dataSet = mutableMapOf<String, Double>()
        dataSet["b"] = target.b
        dataSet["Asb"] = target.Asb
        dataSet["Ast"] = target.Ast
        dataSet["be"] = target.be
        dataSet["bw"] = target.bw
        dataSet["d"] = target.d
        dataSet["dj"] = target.dj
        dataSet["Ec"] = target.Ec
        dataSet["Es"] = target.Es
        dataSet["fc"] = target.fc
        dataSet["Fy"] = target.Fy
        dataSet["h"] = target.h
        dataSet["L"] = target.L
        dataSet["wu"] = target.wu
        dataSet["x"] = target.x
        dataSet["hasConcreteWeb"] = 1.0
        val evaluator = QuantityEvaluator(dataSet, quantitiesToBeEvaluated)
        evaluator.evaluate()
        val assignments = evaluator.assignments

        val ratio_b: Double = evaluator.dataSet["ratio_b"] ?: 0.0
        val messages = assignments.map { it.toString() }.toMutableList()
        val latexLines = assignments.map { it.getLatex() }.toMutableList()
        return RequirementApplication(ratio_b, latexLines, joinMessages(messages))
    }
}