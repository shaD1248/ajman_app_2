package ajman.shd.app1.models.structure

import kotlin.math.max

class AreaLoading(var wsD: Double, var wL: Double, var shouldAddSelfWeight: Boolean = false) {
    var selfWeight: Double = 0.0
    fun calculateSelfWeight(compositeJoist: CompositeJoist) {
    }
    var wD: Double
        get() = if (shouldAddSelfWeight) wsD + selfWeight else wsD
        set(value) {}
    var wu: Double
        get() = max(1.4 * wD, 1.2 * wD + 1.6 * wL)
        set(value) {}
}