package ajman.shayan.joisty.services

import ajman.shayan.joisty.entities.JoistDesign
import ajman.shayan.joisty.entities.PriceList
import ajman.shayan.joisty.models.RequirementApplication
import ajman.shayan.joisty.models.report.Report
import ajman.shayan.joisty.models.structure.AreaLoading
import ajman.shayan.joisty.models.structure.CompositeJoist
import ajman.shayan.joisty.models.structure.ConcreteGrade
import ajman.shayan.joisty.models.structure.JoistArrangement
import ajman.shayan.joisty.models.structure.SteelJoist
import ajman.shayan.joisty.models.structure.SteelSectionDetails
import ajman.shayan.joisty.models.structure.SteelWebDetails
import android.os.Build
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.O)
class JoistDesignService {

    fun getAnalysisReport(joistDesign: JoistDesign, priceList: PriceList): Report {
        val requirementApplication = getRequirementApplication(joistDesign, priceList)
        return Report(requirementApplication.reportSections, joistDesign)
    }

    fun design(joistDesign: JoistDesign, priceList: PriceList) {
        var minCost = Double.POSITIVE_INFINITY
        var joistDesignWithMinCost: JoistDesign = joistDesign.copy()
        val fieldValues = mapOf(
            "steelSectionDetails" to SteelSectionDetails.values().toList(),
            "dj" to listOf(25.0, 30.0, 35.0, 40.0, 45.0),
            "joistArrangement" to JoistArrangement.values().toList(),
            "h" to listOf(5.0, 8.0, 10.0),
            "d" to listOf(30.0, 35.0, 40.0, 45.0, 50.0),
            "concreteGrade" to ConcreteGrade.values().toList(),
        )
        val testCases = generateTestCases(joistDesign, fieldValues)
        for (testCase in testCases) {
            val (isValid, cost) = analyze(testCase, priceList)
            if (cost < minCost && isValid) {
                minCost = cost
                joistDesignWithMinCost = testCase
            }
        }
        fieldValues.keys.forEach {
            set(joistDesign, it, get(joistDesignWithMinCost, it))
        }
    }

    fun generateTestCases(
        joistDesign: JoistDesign,
        fieldValues: Map<String, List<Any>>
    ): List<JoistDesign> {
        val fieldNames = fieldValues.keys.toList()
        val testCases = mutableListOf<JoistDesign>()
        generateTestCasesRecursively(fieldValues, fieldNames, 0, joistDesign, testCases)
        return testCases
    }

    private fun generateTestCasesRecursively(
        fieldValues: Map<String, List<Any>>,
        fieldNames: List<String>,
        currentIndex: Int,
        currentTestCase: JoistDesign,
        testCases: MutableList<JoistDesign>
    ) {
        if (currentIndex == fieldNames.size) {
            // Base case: All fields have values, add the current test case
            testCases.add(currentTestCase.copy())
            return
        }

        val currentFieldName = fieldNames[currentIndex]
        val possibleValues = fieldValues[currentFieldName] ?: emptyList()

        for (value in possibleValues) {
            val updatedTestCase = currentTestCase.copy()
            set(updatedTestCase, currentFieldName, value)
            generateTestCasesRecursively(
                fieldValues,
                fieldNames,
                currentIndex + 1,
                updatedTestCase,
                testCases
            )
        }
    }

    fun analyze(joistDesign: JoistDesign, priceList: PriceList): Pair<Boolean, Double> {
        val requirementApplication = getRequirementApplication(joistDesign, priceList)
        return Pair(requirementApplication.ratio < 1, requirementApplication.dataSet.price.value ?: 0.0)
    }

    private fun getRequirementApplication(
        joistDesign: JoistDesign,
        priceList: PriceList
    ): RequirementApplication {
        val violations = joistDesign.validate()
        if (violations.isNotEmpty()) {
            throw Exception(violations.map { it.message }.joinToString { "\n" })
        }
        val loading = AreaLoading(joistDesign.occupancy)
        val steelJoist = SteelJoist(
            joistDesign.L,
            joistDesign.dj,
            joistDesign.steelSectionDetails,
            SteelWebDetails.T14_20,
            joistDesign.joistArrangement.get_n()
        )
        val compositeJoist = CompositeJoist(
            joistDesign.L,
            steelJoist,
            joistDesign.d,
            joistDesign.h,
            joistDesign.joistArrangement,
            joistDesign.concreteGrade,
            loading
        )
        return RequirementApplication(compositeJoist, priceList)
    }
}