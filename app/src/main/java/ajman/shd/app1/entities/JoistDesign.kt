package ajman.shd.app1.entities

import ajman.shd.app1.enums.Status
import ajman.shd.app1.models.RequirementApplication
import ajman.shd.app1.models.Violation
import ajman.shd.app1.models.requirements.CompositeJoistRequirements
import ajman.shd.app1.models.structure.AreaLoading
import ajman.shd.app1.models.structure.CompositeJoist
import ajman.shd.app1.models.structure.Occupancy
import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.RequiresApi
import java.time.LocalDateTime

data class JoistDesign(var L: Double, var occupancy: Occupancy = Occupancy.RESIDENTIAL): Parcelable {
    var id: Int = 0
    var projectName: String = "Project"
    @RequiresApi(Build.VERSION_CODES.O)
    var createdAt: LocalDateTime = LocalDateTime.now()

    private var _requirementApplication: RequirementApplication = RequirementApplication(0.0)
    private var analysisStatus: Status = Status.NOT_STARTED
    val requirementApplication: RequirementApplication
        get() = _requirementApplication

    fun validate(): Array<Violation> {
        val violations = Array(0) { Violation("", "") }
        if (this.L <= 0) {
            violations.plus(Violation("span", "Span must be greater than 0."))
        }
        return violations
    }

    fun analyze() {
        val violations = validate()
        if (violations.isNotEmpty()) {
            throw Exception(violations.map { it.message }.joinToString { "\n" })
        }
        val loading = AreaLoading(occupancy.get_wD(), occupancy.get_wL())
        val compositeJoist = CompositeJoist(L, loading)
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
            return JoistDesign(0.0, Occupancy.RESIDENTIAL)
        }

        override fun newArray(size: Int): Array<JoistDesign?> {
            return arrayOfNulls(size)
        }
    }
}
