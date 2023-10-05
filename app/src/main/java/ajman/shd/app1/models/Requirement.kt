package ajman.shd.app1.models

abstract class Requirement<type> {
    abstract fun apply(target: type): RequirementApplication
}