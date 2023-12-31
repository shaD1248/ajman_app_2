package ajman.shayan.joisty.models.requirements

//import ajman.shayan.joisty.models.Requirement
//import ajman.shayan.joisty.models.RequirementApplication
//import ajman.shayan.joisty.models.requirements.limit_states.flexure.FlexuralStrengthOfCompositeSection
//import ajman.shayan.joisty.models.requirements.limit_states.shear.TransverseShearStrengthOfCompositeSection
//import ajman.shayan.joisty.models.structure.CompositeJoist
//import ajman.shayan.joisty.models.structure.LocatedCompositeSection
//import java.lang.Double.max
//
//class CompositeJoistRequirements: Requirement<CompositeJoist>() {
//    override val mappedQuantity = ""
//    private val requirements: List<Requirement<LocatedCompositeSection>> = listOf(
//        FlexuralStrengthOfCompositeSection(),
//        TransverseShearStrengthOfCompositeSection(),
//    )
//    override fun apply(target: CompositeJoist): RequirementApplication {
//        target.analyze()
//        var ratio = 0.0
//        var message = ""
//        val latexLines = mutableListOf<String>()
//        requirements.forEach {requirement ->
//            val applications = target.locatedSections.map { locatedCompositeSection ->
//                RequirementApplication(locatedCompositeSection, requirement)
//            }
//            ratio = max(ratio, applications.maxByOrNull { it.ratio }?.ratio ?: 0.0)
//            message += applications.joinToString(separator = "\n") { it.message }
//            latexLines.addAll(applications.flatMap { it.latexLines }.toMutableList())
//        }
//        return RequirementApplication(ratio, latexLines, message)
//    }
//}