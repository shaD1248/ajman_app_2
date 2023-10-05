package ajman.shd.app1.models.requirements

import ajman.shd.app1.models.Requirement
import ajman.shd.app1.models.RequirementApplication
import ajman.shd.app1.models.requirements.limit_states.flexure.FlexuralStrengthOfCompositeSection
import ajman.shd.app1.models.structure.CompositeJoist

class CompositeJoistRequirements: Requirement<CompositeJoist>() {
    override fun apply(target: CompositeJoist): RequirementApplication {
        target.analyze()
        val requirement = FlexuralStrengthOfCompositeSection()
        val applications = mutableListOf<RequirementApplication>()
        for (locatedSection in target.locatedSections) {
            val application = requirement.apply(locatedSection)
            applications.add(application)
        }
        val ratio: Double = applications.maxByOrNull { it.ratio }?.ratio ?: 0.0
        val message: String = applications.joinToString(separator = "\n") { it.message }
        return RequirementApplication(ratio, message)
    }
}