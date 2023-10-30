package ajman.shayan.joisty.models

abstract class Requirement<type> {
    abstract val mappedQuantity: String
    abstract fun apply(target: type): RequirementApplication
}