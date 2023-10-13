package ajman.shd.app1.models.structure

import kotlin.math.min

class LocatedCompositeSection(val x: Double, private val compositeJoist: CompositeJoist) {
    var L: Double
        get() = compositeJoist.L
        set(value) {}
    var wu: Double
        get() = compositeJoist.areaLoading.wu
        set(value) {}
    var d: Double
        get() = compositeJoist.d
        set(value) {}
    var dj: Double
        get() = compositeJoist.steelJoist.dj
        set(value) {}
    var h: Double
        get() = compositeJoist.h
        set(value) {}
    var b: Double
        get() = compositeJoist.b
        set(value) {}
    var bw: Double
        get() = compositeJoist.bw
        set(value) {}
    var be: Double
        get() = min(compositeJoist.b, min(bw + L / 4, bw + 16 * h))
        set(value) {}
    var Asb: Double
        get() = compositeJoist.steelJoist.bottomComponent.A
        set(value) {}
    var Ast: Double
        get() = compositeJoist.steelJoist.topComponent.A
        set(value) {}

    var fc: Double
        get() = compositeJoist.concreteMaterial.F
        set(value) {}
    var Fy: Double
        get() = compositeJoist.steelJoist.bottomComponent.material.F
        set(value) {}
    var Es: Double
        get() = compositeJoist.steelJoist.bottomComponent.material.E
        set(value) {}
    var Ec: Double
        get() = compositeJoist.concreteMaterial.E
        set(value) {}

}