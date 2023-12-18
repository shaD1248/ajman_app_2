package ajman.shayan.joisty.models.structure

import kotlin.math.atan

class JoistTransverseComponent(var A: Double, var s: Double, private val dj: Double) {
    val alpha get() = atan(2 * dj / s)
    var material: Steel = Steel(S235JR)
}