package ajman.shayan.joisty.models

import ajman.shayan.joisty.models.datasets.DataSet
import ajman.shayan.joisty.models.quantity_models.EvaluatableQuantity
import ajman.shayan.joisty.models.templates.Assignment

class QuantityEvaluator(
    var dataSet: DataSet,
    private var quantitiesToBeEvaluated: MutableSet<String>
) {
    val assignments: MutableList<Assignment> = mutableListOf()

    fun evaluate() {
        evaluateRecursively()
        addAssignmentsRecursively()
    }

    private fun evaluateRecursively(): List<EvaluatableQuantity> {
        val visited = mutableSetOf<String>()
        val result = mutableListOf<EvaluatableQuantity>()

        fun visit(quantityName: String) {
            if (quantityName in visited) return
            val quantity = dataSet.get(quantityName) as? EvaluatableQuantity
            quantity?.let {
                visited.add(quantityName)
                it.dependencies.forEach(::visit)
                it.evaluate()
                result.add(it)
            }
        }

        quantitiesToBeEvaluated.forEach { visit(it) }
        return result
    }

    private fun addAssignmentsRecursively(): List<EvaluatableQuantity> {
        val visited = mutableSetOf<EvaluatableQuantity>()
        val result = mutableListOf<EvaluatableQuantity>()

        fun visit(quantity: EvaluatableQuantity) {
            if (quantity in visited) return
            visited.add(quantity)
            quantity.actualDependencies.filterIsInstance<EvaluatableQuantity>().forEach(::visit)
            assignments.addAll(quantity.assignments)
            result.add(quantity)
        }

        quantitiesToBeEvaluated.mapNotNull { dataSet.get(it) as? EvaluatableQuantity }
            .forEach(::visit)
        return result
    }
}