package ajman.shayan.joisty.models.structure

import kotlin.math.min

class LocatedCompositeSection(val x: Double, private val compositeJoist: CompositeJoist) {
    val L: Double get() = compositeJoist.L
    val wD: Double get() = compositeJoist.areaLoading.wD
    val wL: Double get() = compositeJoist.areaLoading.wL
    val d: Double get() = compositeJoist.d
    val dj: Double get() = compositeJoist.steelJoist.dj
    val h: Double get() = compositeJoist.h
    val b: Double get() = compositeJoist.b
    val bw: Double get() = compositeJoist.bw
    val be: Double get() = min(compositeJoist.b, min(bw + L / 4, bw + 16 * h))
    val Asb: Double get() = compositeJoist.steelJoist.bottomComponent.A
    val Ast: Double get() = compositeJoist.steelJoist.topComponent.A
    val Asw: Double get() = compositeJoist.steelJoist.webComponent.A
    val s: Double get() = compositeJoist.steelJoist.webComponent.s
    val n: Int get() = compositeJoist.steelJoist.n

    val fc: Double get() = compositeJoist.concreteMaterial.F
    val Fy: Double get() = compositeJoist.steelJoist.bottomComponent.material.F
    val Fyw: Double get() = compositeJoist.steelJoist.bottomComponent.material.F
    val Es: Double get() = compositeJoist.steelJoist.bottomComponent.material.E
    val Ec: Double get() = compositeJoist.concreteMaterial.E
}