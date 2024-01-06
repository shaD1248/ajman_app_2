package ajman.shayan.joisty.models.templates

import ajman.shayan.joisty.enums.Unit
import ajman.shayan.joisty.services.formatQuantityValue

class Assignment(
    var name: String,
    var value: Double,
    private var unit: Unit,
    private var formula: String? = null
) {
    override fun toString(): String {
        val formattedValue = formatQuantityValue(value, unit)
        val formulaString = formula?.let { " = $it" } ?: ""
        return "$name$formulaString = $formattedValue$unit"
    }
    fun getLatex() = toString()
}