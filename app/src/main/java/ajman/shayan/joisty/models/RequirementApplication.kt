package ajman.shayan.joisty.models

import ajman.shayan.joisty.models.datasets.DataSet
import ajman.shayan.joisty.models.report.Paragraph
import ajman.shayan.joisty.models.report.ReportSection
import ajman.shayan.joisty.models.requirements.limit_states.OccupancyLoading
import ajman.shayan.joisty.models.requirements.limit_states.StructureDeadLoad
import ajman.shayan.joisty.models.requirements.limit_states.deflection.Deflection
import ajman.shayan.joisty.models.requirements.limit_states.flexure.FlexuralStrengthOfCompositeSection
import ajman.shayan.joisty.models.requirements.limit_states.shear.TransverseShearStrengthOfCompositeSection
import ajman.shayan.joisty.models.requirements.limit_states.vibration.NaturalFrequency
import ajman.shayan.joisty.models.structure.CompositeJoist
import ajman.shayan.joisty.models.structure.LocatedCompositeSection

class RequirementApplication(
    private var _ratio: Double,
    private var _reportSections: MutableList<ReportSection> = mutableListOf()
) {
    private val ratio: Double get() = _ratio
    val reportSections: MutableList<ReportSection> get() = _reportSections

    constructor(applications: List<RequirementApplication>) : this(0.0) {
        _ratio = applications.maxByOrNull { it.ratio }?.ratio ?: 0.0
        _reportSections = applications.flatMap { it.reportSections }.toMutableList()
    }

    constructor(compositeJoist: CompositeJoist) : this(0.0) {
        val requirements: List<Requirement<LocatedCompositeSection>> = listOf(
            StructureDeadLoad(),
            OccupancyLoading(),
            FlexuralStrengthOfCompositeSection(),
            TransverseShearStrengthOfCompositeSection(),
            Deflection(),
            NaturalFrequency(),
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
                requirement.updateTableValues(evaluator.dataSet)
                val reportSections = mutableListOf(ReportSection(requirement.titleResourceId, listOf(Paragraph("", assignments.map { it.getLatex() })), requirement.tables))
                RequirementApplication(ratio, reportSections)
            }
        }
        val application = RequirementApplication(applications)
        _ratio = application.ratio
        _reportSections = application.reportSections
    }
}