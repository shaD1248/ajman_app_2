package ajman.shayan.joisty.models

import ajman.shayan.joisty.enums.Unit
import ajman.shayan.joisty.models.datasets.DataSet
import ajman.shayan.joisty.models.quantity_models.EvaluatableQuantity
import ajman.shayan.joisty.models.templates.Assignment

class QuantityEvaluator(
    var dataSet: DataSet,
    private var quantitiesToBeEvaluated: MutableSet<String>
) {
    val assignments: MutableList<Assignment> = mutableListOf()

    fun evaluate() {
        topologicalSort().forEach { quantity ->
            quantity.dependencies
                .filter { dependency -> dataSet.get(dependency)?.value == null }
                .forEach { missingDependency ->
                    assignments.add(Assignment(missingDependency, 0.0, Unit.UNIT, "null"))
                }
            val (_, quantityAssignments, _) = quantity.evaluate()
            assignments.addAll(quantityAssignments)
        }
    }

    private fun topologicalSort(): List<EvaluatableQuantity> {
        val visited = mutableSetOf<String>()
        val result = mutableListOf<EvaluatableQuantity>()

        fun visit(quantityName: String) {
            if (quantityName in visited) return
            val quantity = dataSet.getAllEvaluatableQuantities().firstOrNull { it.name == quantityName } ?: return
            visited.add(quantityName)
            quantity.dependencies.forEach { visit(it) }
            result.add(quantity)
        }

        quantitiesToBeEvaluated.forEach { visit(it) }
        return result
    }
}