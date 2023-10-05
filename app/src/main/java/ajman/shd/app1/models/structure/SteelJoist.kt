package ajman.shd.app1.models.structure

class SteelJoist(L: Double, Asb: Double) {
    var bottomComponent: JoistComponent = JoistComponent(Asb, L)
    var topComponent: JoistComponent = JoistComponent(0.18 * cm2, L)
    var dj: Double = 25.0 * cm
}