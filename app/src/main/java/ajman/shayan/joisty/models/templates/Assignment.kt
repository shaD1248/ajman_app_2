package ajman.shayan.joisty.models.templates

import ajman.shayan.joisty.enums.Unit

class Assignment(
    var name: String,
    var value: Double,
    private var unit: Unit,
    private var formula: String? = null
) {
    private val valueFormat = "%.2f"
    override fun toString(): String {
        val formattedValue = String.format(valueFormat, value / unit.getValue())
        val formulaString = formula?.let { " = $it" } ?: ""
        return "$name$formulaString = $formattedValue$unit"
    }
    fun getLatex() = toString()
}