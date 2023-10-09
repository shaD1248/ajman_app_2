package ajman.shd.app1.models


const val LATEX_LINE_SEPARATOR = "$$$$"
fun joinMessages(messages: MutableList<String>): String {
    return messages.joinToString(separator = "\n") { it }
}
fun joinLatexMessages(messages: MutableList<String>): String {
    return messages.joinToString(separator = LATEX_LINE_SEPARATOR) { it }
}