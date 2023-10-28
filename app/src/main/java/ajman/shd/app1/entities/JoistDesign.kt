package ajman.shd.app1.entities

import ajman.shd.app1.enums.Status
import ajman.shd.app1.models.RequirementApplication
import ajman.shd.app1.models.Violation
import ajman.shd.app1.models.requirements.CompositeJoistRequirements
import ajman.shd.app1.models.structure.AreaLoading
import ajman.shd.app1.models.structure.CompositeJoist
import ajman.shd.app1.models.structure.ConcreteGrade
import ajman.shd.app1.models.structure.SteelSectionDetails
import ajman.shd.app1.models.structure.JoistArrangement
import ajman.shd.app1.models.structure.Occupancy
import ajman.shd.app1.models.structure.SteelJoist
import ajman.shd.app1.models.structure.cm
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
    var dj: Double = 25.0 * cm
    var h: Double = 5.0 * cm
    var d: Double = 30.0 * cm
    var steelSectionDetails = SteelSectionDetails.P_120_3
    var joistArrangement = JoistArrangement.SINGLE_70_WITH_CONCRETE_WEB
    var concreteGrade = ConcreteGrade.C20

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
        val steelJoist = SteelJoist(L, dj, steelSectionDetails, joistArrangement.get_n())
        val compositeJoist = CompositeJoist(
            L,
            steelJoist,
            d,
            h,
            joistArrangement,
            concreteGrade,
            loading
        )
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
