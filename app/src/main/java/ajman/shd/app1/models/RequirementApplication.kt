package ajman.shd.app1.models

class RequirementApplication (private var _ratio: Double, private var _message: String = "") {
    var ratio: Double
        get() = _ratio
        set(value) {}
    var message: String
        get() = _message
        set(value) {}
}