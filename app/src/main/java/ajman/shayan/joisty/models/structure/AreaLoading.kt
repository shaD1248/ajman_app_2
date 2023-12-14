package ajman.shayan.joisty.models.structure

class AreaLoading(private var wsD: Double, var wL: Double, private var shouldAddSelfWeight: Boolean = false) {
    private var selfWeight: Double = 0.0
    fun calculateSelfWeight(compositeJoist: CompositeJoist) {
    }

    val wD: Double get() = if (shouldAddSelfWeight) wsD + selfWeight else wsD
}