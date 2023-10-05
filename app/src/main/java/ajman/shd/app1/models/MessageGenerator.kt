package ajman.shd.app1.models


fun joinMessages(messages: MutableList<String>): String {
    return messages.joinToString(separator = "\n") { it }
}