package ajman.shayan.joisty.models.requirements.limit_states.flexure

import ajman.shayan.joisty.R
import ajman.shayan.joisty.models.Requirement
import ajman.shayan.joisty.models.structure.LocatedCompositeSection

class FlexuralStrengthOfCompositeSection: Requirement<LocatedCompositeSection>() {
    override val mappedQuantity = "ratio_b"
    override val titleResourceId = R.string.report_section_flexural_strength_of_composite_section
}