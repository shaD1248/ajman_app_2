package ajman.shayan.joisty.models.datasets

import ajman.shayan.joisty.models.quantity_models.EvaluatableQuantity
import ajman.shayan.joisty.models.quantities.a
import ajman.shayan.joisty.models.quantities.alpha
import ajman.shayan.joisty.models.quantities.I_composite
import ajman.shayan.joisty.models.quantities.Mn
import ajman.shayan.joisty.models.quantities.Mp
import ajman.shayan.joisty.models.quantities.Mu
import ajman.shayan.joisty.models.quantities.Qsb
import ajman.shayan.joisty.models.quantities.Qst
import ajman.shayan.joisty.models.quantities.Sb
import ajman.shayan.joisty.models.quantities.Sc
import ajman.shayan.joisty.models.quantities.Tp
import ajman.shayan.joisty.models.quantities.epsilon_t
import ajman.shayan.joisty.models.quantities.ns
import ajman.shayan.joisty.models.quantities.phi_b
import ajman.shayan.joisty.models.quantities.phi_v
import ajman.shayan.joisty.models.quantities.qu
import ajman.shayan.joisty.models.quantities.ratio_b
import ajman.shayan.joisty.models.quantities.ratio_v
import ajman.shayan.joisty.models.quantities.Vc
import ajman.shayan.joisty.models.quantities.Vn
import ajman.shayan.joisty.models.quantities.Vs
import ajman.shayan.joisty.models.quantities.Vu
import ajman.shayan.joisty.models.quantities.y_composite
import ajman.shayan.joisty.models.quantity_models.GivenQuantity
import ajman.shayan.joisty.models.quantity_models.NamedQuantity
import ajman.shayan.joisty.models.structure.LocatedCompositeSection

class DataSet(locatedCompositeSection: LocatedCompositeSection) {
    val hasConcreteWeb = true

    val Asb = GivenQuantity("Asb", locatedCompositeSection.Asb)
    val Ast = GivenQuantity("Ast", locatedCompositeSection.Ast)
    val Asw = GivenQuantity("Asw", locatedCompositeSection.Asw)
    val Ec = GivenQuantity("Ec", locatedCompositeSection.Ec)
    val Es = GivenQuantity("Es", locatedCompositeSection.Es)
    val Fy = GivenQuantity("Fy", locatedCompositeSection.Fy)
    val Fyw = GivenQuantity("Fyw", locatedCompositeSection.Fyw)
    val L = GivenQuantity("L", locatedCompositeSection.L)
    val b = GivenQuantity("b", locatedCompositeSection.b)
    val be = GivenQuantity("be", locatedCompositeSection.be)
    val bw = GivenQuantity("bw", locatedCompositeSection.bw)
    val d = GivenQuantity("d", locatedCompositeSection.d)
    val dj = GivenQuantity("dj", locatedCompositeSection.dj)
    val fc = GivenQuantity("fc", locatedCompositeSection.fc)
    val h = GivenQuantity("h", locatedCompositeSection.h)
    val n = GivenQuantity("n", locatedCompositeSection.n.toDouble())
    val s = GivenQuantity("s", locatedCompositeSection.s)
    val wu = GivenQuantity("wu", locatedCompositeSection.wu)
    val x = GivenQuantity("x", locatedCompositeSection.x)
    private fun getGivenQuantities(): Set<GivenQuantity> = setOf(
        Asb, Ast, Asw, Ec, Es, Fy, Fyw, L, b, be, bw, d, dj, fc, h, n, s, wu, x
    )

    val I_composite = I_composite(this)
    val Mn = Mn(this)
    val Mp = Mp(this)
    val Mu = Mu(this)
    val Qsb = Qsb(this)
    val Qst = Qst(this)
    val Sb = Sb(this)
    val Sc = Sc(this)
    val Tp = Tp(this)
    val Vc = Vc(this)
    val Vn = Vn(this)
    val Vs = Vs(this)
    val Vu = Vu(this)
    val a = a(this)
    val alpha = alpha(this)
    val epsilon_t = epsilon_t(this)
    val ns = ns(this)
    val phi_b = phi_b(this)
    val phi_v = phi_v(this)
    val qu = qu(this)
    val ratio_b = ratio_b(this)
    val ratio_v = ratio_v(this)
    val y_composite = y_composite(this)
    fun getEvaluatableQuantities(): Set<EvaluatableQuantity> = setOf(
        I_composite, Mn, Mp, Mu, Qsb, Qst, Sb, Sc, Tp, Vc, Vn, Vs, Vu, a, alpha, epsilon_t, ns,
        phi_b, phi_v, qu, ratio_b, ratio_v, y_composite
    )

    private fun getAllQuantities() = getGivenQuantities() + getEvaluatableQuantities()
    fun get(quantityName: String): NamedQuantity? = getAllQuantities().find { it.name == quantityName }
}