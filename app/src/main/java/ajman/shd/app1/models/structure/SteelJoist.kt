package ajman.shd.app1.models.structure

class SteelJoist(
    L: Double,
    var dj: Double,
    steelSectionDetails: SteelSectionDetails,
    var n: Int = 1
) {
    var bottomComponent: JoistComponent = JoistComponent(n * steelSectionDetails.get_Asb(), L)
    var topComponent: JoistComponent = JoistComponent(n * steelSectionDetails.get_Ast(), L)
}