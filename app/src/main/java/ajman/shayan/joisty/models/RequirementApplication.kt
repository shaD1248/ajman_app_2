package ajman.shayan.joisty.models

import ajman.shayan.joisty.entities.PriceList
import ajman.shayan.joisty.models.datasets.DataSet
import ajman.shayan.joisty.models.report.Paragraph
import ajman.shayan.joisty.models.report.ReportSection
import ajman.shayan.joisty.models.requirements.limit_states.OccupancyLoading
import ajman.shayan.joisty.models.requirements.limit_states.PriceCalculation
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
    val ratio: Double get() = _ratio
    lateinit var dataSet: DataSet
    val reportSections: MutableList<ReportSection> get() = _reportSections

    constructor(applications: List<RequirementApplication>) : this(0.0) {
        _ratio = applications.maxByOrNull { it.ratio }?.ratio ?: 0.0
        _reportSections = applications.flatMap { it.reportSections }.toMutableList()
    }

    constructor(compositeJoist: CompositeJoist, priceList: PriceList?) : this(0.0) {
        val requirements: List<Requirement<LocatedCompositeSection>> = listOf(
            StructureDeadLoad(),
            OccupancyLoading(),
            FlexuralStrengthOfCompositeSection(),
            TransverseShearStrengthOfCompositeSection(),
            Deflection(),
            NaturalFrequency(),
            PriceCalculation(),
        )
        compositeJoist.analyze()
        val applications = compositeJoist.locatedSections.flatMap { locatedCompositeSection ->
            dataSet = DataSet(locatedCompositeSection, priceList)
            val quantitiesToBeEvaluated = requirements.map { it.mappedQuantity }.toMutableSet()
            val evaluator = QuantityEvaluator(dataSet, quantitiesToBeEvaluated)
            evaluator.evaluate()
            requirements.map { requirement ->
                val mappedQuantity = requirement.mappedQuantity
                val assignments = evaluator.mappedAssignments[mappedQuantity] ?: mutableListOf()
                val ratio = dataSet.get(mappedQuantity)?.value ?: 0.0
                requirement.updateTableValues(dataSet)
                val reportSections = mutableListOf(ReportSection(requirement.titleResourceId, listOf(Paragraph("", assignments.map { it.getLatex() })), requirement.tables))
                RequirementApplication(ratio, reportSections)
            }
        }
        val application = RequirementApplication(applications)
        _ratio = application.ratio
        _reportSections = application.reportSections
    }
}