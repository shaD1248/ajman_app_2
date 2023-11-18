package ajman.shayan.joisty.models.requirements.limit_states.shear

import ajman.shayan.joisty.models.QuantityEvaluator
import ajman.shayan.joisty.models.Requirement
import ajman.shayan.joisty.models.RequirementApplication
import ajman.shayan.joisty.models.datasets.DataSet
import ajman.shayan.joisty.models.structure.LocatedCompositeSection

class TransverseShearStrengthOfCompositeSection: Requirement<LocatedCompositeSection>() {
    override val mappedQuantity = "ratio_v"
}