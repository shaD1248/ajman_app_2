package ajman.shd.app1.models.requirements

import ajman.shd.app1.models.Requirement
import ajman.shd.app1.models.RequirementApplication
import ajman.shd.app1.models.requirements.limit_states.flexure.FlexuralStrengthOfCompositeSection
import ajman.shd.app1.models.structure.CompositeJoist

class CompositeJoistRequirements: Requirement<CompositeJoist>() {
    override val mappedQuantity = ""
    override fun apply(target: CompositeJoist): RequirementApplication {
        target.analyze()
        val requirement = FlexuralStrengthOfCompositeSection()
        val applications = target.locatedSections.map { requirement.apply(it) }
        val ratio: Double = applications.maxByOrNull { it.ratio }?.ratio ?: 0.0
        val message: String = applications.joinToString(separator = "\n") { it.message }
        val latexLines = applications.flatMap { it.latexLines }.toMutableList()
        return RequirementApplication(ratio, latexLines, message)
    }
}