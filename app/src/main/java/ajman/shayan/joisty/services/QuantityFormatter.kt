package ajman.shayan.joisty.services

import ajman.shayan.joisty.enums.Unit

const val QUANTITY_VALUE_FORMAT = "%.2f"
fun formatQuantityValue(value: Double?, unit: Unit): String {
    return String.format(QUANTITY_VALUE_FORMAT, (value ?: 0.0) / unit.getValue())
}