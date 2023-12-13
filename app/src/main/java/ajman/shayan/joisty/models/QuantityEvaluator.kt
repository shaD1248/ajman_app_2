package ajman.shayan.joisty.models

import ajman.shayan.joisty.models.datasets.DataSet
import ajman.shayan.joisty.models.quantity_models.EvaluatableQuantity
import ajman.shayan.joisty.models.templates.Assignment

class QuantityEvaluator(
    var dataSet: DataSet,
    private var quantitiesToBeEvaluated: MutableSet<String>
) {
    val mappedAssignments: MutableMap<String, MutableList<Assignment>> = mutableMapOf()

    fun evaluate() {
        evaluateRecursively()
        addMappedAssignmentsRecursively()
    }

    private fun evaluateRecursively() {
        val visited = mutableSetOf<String>()

        fun visit(quantityName: String) {
            if (quantityName in visited) return
            val quantity = dataSet.get(quantityName) as? EvaluatableQuantity
            quantity?.let {
                visited.add(quantityName)
                it.dependencies.forEach(::visit)
                it.evaluate()
            }
        }

        quantitiesToBeEvaluated.forEach { visit(it) }
    }

    private fun addMappedAssignmentsRecursively() {
        val visited = mutableSetOf<EvaluatableQuantity>()

        fun visit(key: String, quantity: EvaluatableQuantity) {
            if (quantity in visited) return
            visited.add(quantity)
            quantity.actualDependencies.filterIsInstance<EvaluatableQuantity>()
                .forEach { visit(key, it) }
            mappedAssignments.computeIfAbsent(key) { mutableListOf() }.addAll(quantity.assignments)
        }

        quantitiesToBeEvaluated.mapNotNull { dataSet.get(it) as? EvaluatableQuantity }
            .forEach { visit(it.name, it) }
    }
}