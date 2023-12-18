package ajman.shayan.joisty.models.structure

class CompositeJoist(
    var L: Double,
    val steelJoist: SteelJoist,
    var d: Double,
    var h: Double,
    var joistArrangement: JoistArrangement,
    concreteGrade: ConcreteGrade,
    val areaLoading: AreaLoading
) {
    var b: Double = joistArrangement.get_b()
    private var bb: Double = joistArrangement.get_bb()
    var bw: Double = if (joistArrangement.hasConcreteWeb()) b - bb else 0.0
    var concreteMaterial = concreteGrade.getConcrete()
    var locatedSections = mutableListOf<LocatedCompositeSection>()

    init {
        areaLoading.calculateSelfWeight(this)
    }
    fun analyze() {
        val section = LocatedCompositeSection(L / 2, this)
        locatedSections.add(section)
    }
}