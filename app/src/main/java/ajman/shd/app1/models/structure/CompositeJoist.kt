package ajman.shd.app1.models.structure

class CompositeJoist(var L: Double, val areaLoading: AreaLoading, var hasConcreteWeb: Boolean = true) {
    var d: Double = 30 * cm
    var h: Double = 5 * cm
    var b: Double = 70 * cm
    var bw: Double = if (hasConcreteWeb) b - 66.0 * cm else 0.0
    var lineLoading = LineLoading(areaLoading, this)
    var steelJoist = SteelJoist(L, 3.6 * cm2)
    var concreteMaterial = Concrete(C20)
    var locatedSections = mutableListOf<LocatedCompositeSection>()
    fun analyze() {
        val section = LocatedCompositeSection(L / 2, this)
        locatedSections.add(section)
    }
}