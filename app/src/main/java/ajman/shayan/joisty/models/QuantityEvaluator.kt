package ajman.shayan.joisty.models

import ajman.shayan.joisty.enums.Unit
import ajman.shayan.joisty.models.quantities.I_composite
import ajman.shayan.joisty.models.quantities.Mn
import ajman.shayan.joisty.models.quantities.Mp
import ajman.shayan.joisty.models.quantities.Mu
import ajman.shayan.joisty.models.quantities.Qsb
import ajman.shayan.joisty.models.quantities.Qst
import ajman.shayan.joisty.models.quantities.Sb
import ajman.shayan.joisty.models.quantities.Sc
import ajman.shayan.joisty.models.quantities.Tp
import ajman.shayan.joisty.models.quantities.a
import ajman.shayan.joisty.models.quantities.epsilon_t
import ajman.shayan.joisty.models.quantities.ns
import ajman.shayan.joisty.models.quantities.phi_b
import ajman.shayan.joisty.models.quantities.qu
import ajman.shayan.joisty.models.quantities.ratio_b
import ajman.shayan.joisty.models.quantities.y_composite
import ajman.shayan.joisty.models.templates.Assignment

class QuantityEvaluator(
    var dataSet: MutableMap<String, Double>,
    private var quantitiesToBeEvaluated: MutableSet<String>
) {
    private val allQuantities: MutableSet<Quantity> = mutableSetOf(
        a(), epsilon_t(), I_composite(), Mn(), Mp(), Mu(), ns(), phi_b(), Qsb(), Qst(), qu(),
        ratio_b(), Sb(), Sc(), Tp(), y_composite()
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