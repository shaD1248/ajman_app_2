package ajman.shayan.joisty.entities

import ajman.shayan.joisty.models.Violation
import ajman.shayan.joisty.models.structure.ConcreteGrade
import ajman.shayan.joisty.models.structure.SteelSectionDetails
import ajman.shayan.joisty.models.structure.JoistArrangement
import ajman.shayan.joisty.models.structure.Occupancy
import ajman.shayan.joisty.models.structure.cm
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Ignore
import androidx.room.Index
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(
    tableName = "joist_design",
    indices = [Index(value = ["loading_id"])],
    foreignKeys = [
//        ForeignKey(entity = Loading::class, parentColumns = ["id"], childColumns = ["loading_id"]),
    ],
)
@RequiresApi(Build.VERSION_CODES.O)
data class JoistDesign(@ColumnInfo(name = "L") var L: Double) {
    @PrimaryKey(autoGenerate = true) var id: Long = 0
    @ColumnInfo(name = "occupancy") var occupancy: Occupancy = Occupancy.RESIDENTIAL
    @ColumnInfo(name = "loading_id", defaultValue = "0") var loadingId: Long = 0
    @Ignore var loading: Loading? = null
    @ColumnInfo(name = "project_name") var projectName: String = ""
    @ColumnInfo(name = "created_at") var createdAt: LocalDateTime = LocalDateTime.now()
    @ColumnInfo(name = "updated_at", defaultValue = "2023-12-19T00:00:00.000000") var updatedAt: LocalDateTime = LocalDateTime.now()
    @ColumnInfo(name = "dj") var dj: Double = 25.0 * cm
    @ColumnInfo(name = "h") var h: Double = 5.0 * cm
    @ColumnInfo(name = "d") var d: Double = 30.0 * cm
    @ColumnInfo(name = "steel_section_details") var steelSectionDetails = SteelSectionDetails.PL_120_3
    @ColumnInfo(name = "joist_arrangement") var joistArrangement = JoistArrangement.SINGLE_70_WITH_CONCRETE_WEB
    @ColumnInfo(name = "concrete_grade") var concreteGrade = ConcreteGrade.C20
    @Ignore var selected = false

    fun validate(): Array<Violation> {
        val violations = Array(0) { Violation("", "") }
        if (this.L <= 0) {
            violations.plus(Violation("span", "Span must be greater than 0."))
        }
        return violations
    }
}
