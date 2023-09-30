package ajman.shd.app1.entities

import ajman.shd.app1.enums.Status
import ajman.shd.app1.models.Violation
import kotlin.math.pow

data class JoistDesign(var _span: Double, var _load: Double) {
    private var _shear: Double = 0.0
    private var _moment: Double = 0.0
    private var analysisStatus: Status = Status.NOT_STARTED
    var span: Double
        get() = _span
        set(value) {
            _span = value
            analysisStatus = Status.NOT_STARTED
        }
    var load: Double
        get() = _load
        set(value) {
            _load = value
            analysisStatus = Status.NOT_STARTED
        }
    var shear: Double
        get() = _shear
        set(value) {}
    var moment: Double
        get() = _moment
        set(value) {}

    fun getAnalysisStatus(): Status {
        return analysisStatus
    }

    fun validate(): Array<Violation> {
        val violations = Array(0) { Violation("", "") }
        if (_span <= 0) {
            violations.plus(Violation("span", "Span must be greater than 0."))
        }
        return violations
    }

    fun analyze() {
        val violations = validate()
        if (violations.isNotEmpty()) {
            throw Exception(violations.map { it.message }.joinToString { "\n" })
        }
        _shear = 0.5 * _load * _span
        _moment = 0.125 * _load * _span.pow(2.0)
        analysisStatus = Status.COMPLETED
    }
}
