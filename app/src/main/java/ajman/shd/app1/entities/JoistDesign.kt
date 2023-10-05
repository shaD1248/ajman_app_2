package ajman.shd.app1.entities

import ajman.shd.app1.enums.Status
import ajman.shd.app1.models.RequirementApplication
import ajman.shd.app1.models.Violation
import ajman.shd.app1.models.requirements.CompositeJoistRequirements
import ajman.shd.app1.models.structure.CompositeJoist
import ajman.shd.app1.models.structure.AreaLoading
import kotlin.math.pow

data class JoistDesign(var _span: Double, var _load: Double) {
    private var _shear: Double = 0.0
    private var _moment: Double = 0.0
    private var _requirementApplication: RequirementApplication = RequirementApplication(0.0)
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
    var requirementApplication: RequirementApplication
        get() = _requirementApplication
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
        val loading = AreaLoading(0.0, _load)
        val compositeJoist = CompositeJoist(_span, loading)
        val compositeJoistRequirements = CompositeJoistRequirements()
        _requirementApplication = compositeJoistRequirements.apply(compositeJoist)
        analysisStatus = Status.COMPLETED
    }
}
