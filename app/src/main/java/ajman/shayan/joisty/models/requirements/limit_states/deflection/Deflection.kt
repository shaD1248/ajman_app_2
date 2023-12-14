package ajman.shayan.joisty.models.requirements.limit_states.deflection

import ajman.shayan.joisty.R
import ajman.shayan.joisty.models.Requirement
import ajman.shayan.joisty.models.structure.LocatedCompositeSection

class Deflection: Requirement<LocatedCompositeSection>() {
    override val mappedQuantity = "ratio_Delta"
    override val titleResourceId = R.string.report_section_deflection
}