package ajman.shd.app1.models.structure

import kotlin.math.max
import kotlin.math.min
import kotlin.math.pow

class LocatedCompositeSection(val x: Double, private val compositeJoist: CompositeJoist) {
    var L: Double
        get() = compositeJoist.L
        set(value) {}
    var qu: Double
        get() = compositeJoist.lineLoading.qu
        set(value) {}
    var hasConcreteWeb: Boolean
        get() = compositeJoist.hasConcreteWeb
        set(value) {}
    var d: Double
        get() = compositeJoist.d
        set(value) {}
    var h: Double
        get() = compositeJoist.h
        set(value) {}
    var bw: Double
        get() = compositeJoist.bw
        set(value) {}
    var be: Double
        get() = min(compositeJoist.b, min(bw + L / 4, bw + 16 * h))
        set(value) {}
    var ns: Double
        get() = Es / Ec
        set(value) {}
    var Asb: Double
        get() = compositeJoist.steelJoist.bottomComponent.A
        set(value) {}
    var Ast: Double
        get() = compositeJoist.steelJoist.topComponent.A
        set(value) {}
    var Qsb: Double
        get() = Asb * d
        set(value) {}
    var Qst: Double
        get() = Ast * (d - compositeJoist.steelJoist.dj)
        set(value) {}

    private var _y_composite: Double? = null
    var y_composite: Double
        get() {
            if (_y_composite == null) {
                _y_composite = solve_quadratic(be / 2 / ns, Asb, -Qsb)
                if ((_y_composite ?: 0.0) > h) {
                    _y_composite = solve_quadratic(
                        bw / 2 / ns,
                        Asb + (be - bw) / ns * h,
                        -(Qsb + (be - bw) / 2 / ns * h.pow(2))
                    )
                }
            }
            return _y_composite ?: 0.0
        }
        set(value) {}

    private var _I_composite: Double? = null
    var I_composite: Double
        get() {
            if (_I_composite == null) {
                val I_c = be / 3 / ns * y_composite.pow(3) + Asb * (Qsb / Asb - y_composite).pow(2) - (be - bw) / 3 / ns * max(y_composite - h, 0.0).pow(3)
                val I_sb = Asb * d.pow(2) - 2 * Qsb * y_composite + Asb * y_composite.pow(2)
                val I_st = Ast * d.pow(2) - 2 * Qst * y_composite + Ast * y_composite.pow(2)
                _I_composite = I_c + I_sb + I_st
            }
            return _I_composite ?: 0.0
        }
        set(value) {}
    var Sb: Double
        get() = I_composite / (d - y_composite)
        set(value) {}
    var Sc: Double
        get() = -ns * I_composite / y_composite
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
    var Tp: Double
        get() = Asb * Fy
        set(value) {}
    var Mp: Double
        get() = Asb * Fy * d
        set(value) {}

    private fun solve_quadratic(a: Double, b: Double, c: Double): Double {
        return (-b + (b.pow(2) - 4 * a * c).pow(0.5)) / 2 / a
    }
}