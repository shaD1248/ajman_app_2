package ajman.shayan.joisty.models.requirements.limit_states.vibration

import ajman.shayan.joisty.R
import ajman.shayan.joisty.models.Requirement
import ajman.shayan.joisty.models.structure.LocatedCompositeSection

class NaturalFrequency: Requirement<LocatedCompositeSection>() {
    override val mappedQuantity = "ratio_f"
    override val titleResourceId = R.string.report_section_natural_frequency
}