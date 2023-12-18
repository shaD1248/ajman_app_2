package ajman.shayan.joisty.models.structure

class SteelJoist(
    L: Double,
    var dj: Double,
    steelSectionDetails: SteelSectionDetails,
    steelWebDetails: SteelWebDetails,
    var n: Int = 1
) {
    var bottomComponent: JoistLongitudinalComponent = JoistLongitudinalComponent(n * steelSectionDetails.get_Asb(), L)
    var topComponent: JoistLongitudinalComponent = JoistLongitudinalComponent(n * steelSectionDetails.get_Ast(), L)
    var webComponent: JoistTransverseComponent = JoistTransverseComponent(n * steelWebDetails.get_Asw(), steelWebDetails.get_s(), dj)
}