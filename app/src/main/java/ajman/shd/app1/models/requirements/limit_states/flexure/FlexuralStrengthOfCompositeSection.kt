package ajman.shd.app1.models.requirements.limit_states.flexure

import ajman.shd.app1.models.Requirement
import ajman.shd.app1.models.RequirementApplication
import ajman.shd.app1.models.generateAssignment
import ajman.shd.app1.models.generateAssignmentWithFormula
import ajman.shd.app1.models.joinMessages
import ajman.shd.app1.models.structure.LocatedCompositeSection
import ajman.shd.app1.models.structure.kgf
import ajman.shd.app1.models.structure.m
import kotlin.math.min
import kotlin.math.pow

class FlexuralStrengthOfCompositeSection: Requirement<LocatedCompositeSection>() {
    override fun apply(target: LocatedCompositeSection): RequirementApplication {
        val Mu: Double = 0.5 * target.qu * target.x * (target.L - target.x)
        val phi_b: Double
        val Mn: Double
        if (target.hasConcreteWeb) {
            val a = calculate_a(target)
            phi_b = calculate_phi_b_with_concrete_web(target, a)
            Mn = calculate_Mn_with_concrete_web(target, a)
        } else {
            phi_b = 0.9
            Mn = min(target.Fy * target.Sb, -0.7 * target.fc * target.Sc)
        }
        val ratio = Mu / phi_b / Mn
        val messages = mutableListOf(
            generateAssignment("Mu", Mu / kgf / m, "%.2f", "kgfm"),
            generateAssignment("phi_b", phi_b, "%.2f", ""),
            generateAssignment("Mn", Mn / kgf / m, "%.2f", "kgfm"),
            generateAssignmentWithFormula("ratio", "Mu / phi_b / Mn", ratio, "%.2f", ""),
        )
        return RequirementApplication(ratio, joinMessages(messages))
    }

    private fun calculate_a(target: LocatedCompositeSection): Double {
        var a = target.Tp / 0.85 / target.fc / target.be
        if (a > target.h) {
            a = target.Tp / 0.85 / target.fc / target.bw - (target.be / target.bw - 1) * target.h
        }
        return a
    }

    private fun calculate_phi_b_with_concrete_web(target: LocatedCompositeSection, a: Double): Double {
        val epsilon_t = 0.003 * (0.85 * target.d / a - 1)
        val phi_b = if (epsilon_t >= 0.005) {
            0.9
        } else if (epsilon_t > target.Fy / target.Es) {
            0.9 - (0.9 - 0.65) * (0.005 - epsilon_t) / (0.005 - target.Fy / target.Es)
        } else {
            0.65
        }
        return phi_b
    }

    private fun calculate_Mn_with_concrete_web(target: LocatedCompositeSection, a: Double): Double {
        val Mc: Double = if (a <= target.h) {
            0.5 * a.pow(2) * target.be
        } else {
            0.5 * a.pow(2) * target.bw - 0.5 * (target.be - target.bw) * target.h.pow(2)
        }
        return target.Mp - 0.85 * target.fc * Mc
    }
}