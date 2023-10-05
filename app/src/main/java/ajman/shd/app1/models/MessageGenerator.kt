package ajman.shd.app1.models

fun generateAssignment(name: String, value: Double, format: String, unit: String): String {
    return String.format("%s = %s%s", name, String.format(format, value), unit)
}

fun generateAssignmentWithFormula(name: String, formula: String, value: Double, format: String, unit: String): String {
    return generateAssignment(String.format("%s = %s", name, formula), value, format, unit)
}

fun joinMessages(messages: MutableList<String>): String {
    return messages.joinToString(separator = "\n") { it }
}