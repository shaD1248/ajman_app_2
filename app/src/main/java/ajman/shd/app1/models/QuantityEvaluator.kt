package ajman.shd.app1.models

import ajman.shd.app1.enums.Unit
import ajman.shd.app1.models.quantities.Mn
import ajman.shd.app1.models.quantities.Mu
import ajman.shd.app1.models.quantities.a
import ajman.shd.app1.models.quantities.epsilon_t
import ajman.shd.app1.models.quantities.phi_b
import ajman.shd.app1.models.quantities.ratio_b
import ajman.shd.app1.models.templates.Assignment

class QuantityEvaluator(
    var dataSet: MutableMap<String, Double>,
    private var quantitiesToBeEvaluated: MutableSet<String>
) {
    private val allQuantities: MutableSet<Quantity> = mutableSetOf(
        a(), epsilon_t(), Mn(), Mu(), phi_b(), /*qu(),*/ ratio_b()
    )
    val assignments: MutableList<Assignment> = mutableListOf()

    fun evaluate() {
        topologicalSort().forEach { quantity ->
            quantity.dependencies
                .filter { dependency -> dataSet[dependency] == null }
                .forEach { missingDependency ->
                    assignments.add(Assignment(missingDependency, 0.0, Unit.UNIT, "null"))
                }
            val (quantityValue, quantityAssignments) = quantity.evaluate(dataSet)
            dataSet[quantity.name] = quantityValue
            assignments.addAll(quantityAssignments)
        }
    }

    private fun topologicalSort(): List<Quantity> {
        val visited = mutableSetOf<String>()
        val result = mutableListOf<Quantity>()

        fun visit(quantityName: String) {
            if (quantityName in visited) return
            val quantity = allQuantities.firstOrNull { it.name == quantityName } ?: return
            visited.add(quantityName)
            quantity.dependencies.forEach { visit(it) }
            result.add(quantity)
        }

        quantitiesToBeEvaluated.forEach { visit(it) }
        return result
    }
}