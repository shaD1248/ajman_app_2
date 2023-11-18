package ajman.shayan.joisty.models

import ajman.shayan.joisty.models.datasets.DataSet
import ajman.shayan.joisty.models.requirements.limit_states.flexure.FlexuralStrengthOfCompositeSection
import ajman.shayan.joisty.models.requirements.limit_states.shear.TransverseShearStrengthOfCompositeSection
import ajman.shayan.joisty.models.structure.CompositeJoist
import ajman.shayan.joisty.models.structure.LocatedCompositeSection

class RequirementApplication(
    private var _ratio: Double,
    private var _latexLines: MutableList<String> = mutableListOf(),
    private var _message: String = ""
) {
    val ratio: Double get() = _ratio
    val latexLines: MutableList<String> get() = _latexLines
    val message: String get() = _message

    constructor(applications: List<RequirementApplication>) : this(0.0) {
        _ratio = applications.maxByOrNull { it.ratio }?.ratio ?: 0.0
        _message = applications.joinToString(separator = "\n") { it.message }
        _latexLines = applications.flatMap { it.latexLines }.toMutableList()
    }

    constructor(compositeJoist: CompositeJoist) : this(0.0) {
        val requirements: List<Requirement<LocatedCompositeSection>> = listOf(
            FlexuralStrengthOfCompositeSection(),
            TransverseShearStrengthOfCompositeSection(),
        )
        compositeJoist.analyze()
        val applications = compositeJoist.locatedSections.flatMap { locatedCompositeSection ->
            val dataSet = DataSet(locatedCompositeSection)
            val quantitiesToBeEvaluated = requirements.map { it.mappedQuantity }.toMutableSet()
            val evaluator = QuantityEvaluator(dataSet, quantitiesToBeEvaluated)
            evaluator.evaluate()
            requirements.map { requirement ->
                val mappedQuantity = requirement.mappedQuantity
                val assignments = evaluator.mappedAssignments[mappedQuantity] ?: mutableListOf()
                val ratio = evaluator.dataSet.get(mappedQuantity)?.value ?: 0.0
                val latexLines = assignments.map { it.getLatex() }.toMutableList()
                val message = joinMessages(assignments.map { it.toString() }.toMutableList())
                RequirementApplication(ratio, latexLines, message)
            }
        }
        val application = RequirementApplication(applications)
        _ratio = application.ratio
        _latexLines = application.latexLines
        _message = application.message
    }
}