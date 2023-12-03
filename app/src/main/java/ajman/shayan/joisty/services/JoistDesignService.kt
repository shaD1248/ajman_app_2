package ajman.shayan.joisty.services

import ajman.shayan.joisty.entities.JoistDesign
import ajman.shayan.joisty.enums.Status
import ajman.shayan.joisty.models.RequirementApplication
import ajman.shayan.joisty.models.report.Report
import ajman.shayan.joisty.models.structure.AreaLoading
import ajman.shayan.joisty.models.structure.CompositeJoist
import ajman.shayan.joisty.models.structure.SteelJoist
import ajman.shayan.joisty.models.structure.SteelWebDetails
import android.os.Build
import androidx.annotation.RequiresApi

class JoistDesignService {
    private var analysisStatus: Status = Status.NOT_STARTED

    @RequiresApi(Build.VERSION_CODES.O)
    fun analyze(joistDesign: JoistDesign): Report {
        val violations = joistDesign.validate()
        if (violations.isNotEmpty()) {
            throw Exception(violations.map { it.message }.joinToString { "\n" })
        }
        val loading = AreaLoading(joistDesign.occupancy.get_wD(), joistDesign.occupancy.get_wL())
        val steelJoist = SteelJoist(
            joistDesign.L,
            joistDesign.dj,
            joistDesign.steelSectionDetails,
            SteelWebDetails.T14_20,
            joistDesign.joistArrangement.get_n()
        )
        val compositeJoist = CompositeJoist(
            joistDesign.L,
            steelJoist,
            joistDesign.d,
            joistDesign.h,
            joistDesign.joistArrangement,
            joistDesign.concreteGrade,
            loading
        )
        val requirementApplication = RequirementApplication(compositeJoist)
        analysisStatus = Status.COMPLETED
        return Report(requirementApplication.reportSections, joistDesign)
    }
}