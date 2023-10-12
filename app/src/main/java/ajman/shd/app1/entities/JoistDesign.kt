package ajman.shd.app1.entities

import ajman.shd.app1.enums.Status
import ajman.shd.app1.models.RequirementApplication
import ajman.shd.app1.models.Violation
import ajman.shd.app1.models.requirements.CompositeJoistRequirements
import ajman.shd.app1.models.structure.CompositeJoist
import ajman.shd.app1.models.structure.AreaLoading
import ajman.shd.app1.models.structure.Occupancy
import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import android.text.Editable
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import kotlin.math.pow

val editableFactory = Editable.Factory.getInstance()!!

data class JoistDesign(var _span: Double, var _load: Double): Parcelable {
    var id: Int = 0
    var projectName: String = ""
    @RequiresApi(Build.VERSION_CODES.O)
    var createdAt: LocalDateTime = LocalDateTime.now()
    var joistLength: Double = _span
    var occupancy: Occupancy = Occupancy.RESIDENTIAL

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
    val requirementApplication: RequirementApplication
        get() = _requirementApplication

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

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(p0: Parcel, p1: Int) {
    }

    companion object CREATOR : Parcelable.Creator<JoistDesign> {
        override fun createFromParcel(parcel: Parcel): JoistDesign {
            // Read the parcel and create an instance of JoistDesign
            return JoistDesign(0.0, 0.0)
        }

        override fun newArray(size: Int): Array<JoistDesign?> {
            return arrayOfNulls(size)
        }
    }
}
