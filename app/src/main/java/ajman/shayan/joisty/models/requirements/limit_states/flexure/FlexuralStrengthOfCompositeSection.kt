package ajman.shayan.joisty.models.requirements.limit_states.flexure

import ajman.shayan.joisty.models.QuantityEvaluator
import ajman.shayan.joisty.models.Requirement
import ajman.shayan.joisty.models.RequirementApplication
import ajman.shayan.joisty.models.datasets.DataSet
import ajman.shayan.joisty.models.structure.LocatedCompositeSection

class FlexuralStrengthOfCompositeSection: Requirement<LocatedCompositeSection>() {
    override val mappedQuantity = "ratio_b"
    override fun apply(target: LocatedCompositeSection): RequirementApplication {
        val dataSet = DataSet(target)
        val quantitiesToBeEvaluated = mutableSetOf("ratio_b")
        val evaluator = QuantityEvaluator(dataSet, quantitiesToBeEvaluated)
        evaluator.evaluate()
        val assignments = evaluator.assignments

        val ratio_b: Double = evaluator.dataSet.ratio_b.value ?: 0.0
        val messages = assignments.map { it.toString() }.toMutableList()
        val latexLines = assignments.map { it.getLatex() }.toMutableList()
        return RequirementApplication(ratio_b, latexLines,
            ajman.shayan.joisty.models.joinMessages(messages)
        )
    }
}