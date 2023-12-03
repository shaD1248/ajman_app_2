package ajman.shayan.joisty.models.requirements.limit_states.shear

import ajman.shayan.joisty.R
import ajman.shayan.joisty.models.Requirement
import ajman.shayan.joisty.models.structure.LocatedCompositeSection

class TransverseShearStrengthOfCompositeSection: Requirement<LocatedCompositeSection>() {
    override val mappedQuantity = "ratio_v"
    override val titleResourceId = R.string.report_section_transverse_shear_strength_of_composite_section
}