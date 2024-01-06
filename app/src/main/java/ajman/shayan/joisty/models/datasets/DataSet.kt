package ajman.shayan.joisty.models.datasets

import ajman.shayan.joisty.models.quantities.Delta_DL
import ajman.shayan.joisty.models.quantities.Delta_L
import ajman.shayan.joisty.models.quantities.Delta_aDL
import ajman.shayan.joisty.models.quantities.Delta_aL
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
import ajman.shayan.joisty.models.quantities.qL
import ajman.shayan.joisty.models.quantities.ratio_b
import ajman.shayan.joisty.models.quantities.ratio_v
import ajman.shayan.joisty.models.quantities.Vc
import ajman.shayan.joisty.models.quantities.Vn
import ajman.shayan.joisty.models.quantities.Vs
import ajman.shayan.joisty.models.quantities.Vu
import ajman.shayan.joisty.models.quantities.fn
import ajman.shayan.joisty.models.quantities.fn_min
import ajman.shayan.joisty.models.quantities.q
import ajman.shayan.joisty.models.quantities.qD
import ajman.shayan.joisty.models.quantities.ratio_Delta
import ajman.shayan.joisty.models.quantities.ratio_f
import ajman.shayan.joisty.models.quantities.w
import ajman.shayan.joisty.models.quantities.wD
import ajman.shayan.joisty.models.quantities.wcD
import ajman.shayan.joisty.models.quantities.wu
import ajman.shayan.joisty.models.quantities.y_composite
import ajman.shayan.joisty.models.quantity_models.GivenQuantity
import ajman.shayan.joisty.models.quantity_models.NamedQuantity
import ajman.shayan.joisty.models.structure.LocatedCompositeSection

class DataSet(locatedCompositeSection: LocatedCompositeSection) {
    val hasConcreteWeb = true
    val shouldAddSelfWeight = locatedCompositeSection.compositeJoist.areaLoading.shouldAddSelfWeight

    val occupancy = locatedCompositeSection.compositeJoist.areaLoading.occupancy

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
    val g = GivenQuantity("g", ajman.shayan.joisty.models.structure.g)
    val h = GivenQuantity("h", locatedCompositeSection.h)
    val n = GivenQuantity("n", locatedCompositeSection.n.toDouble())
    val s = GivenQuantity("s", locatedCompositeSection.s)
    val wsD = GivenQuantity("wsD", locatedCompositeSection.compositeJoist.areaLoading.occupancy.wsD)
    val wL = GivenQuantity("wL", locatedCompositeSection.wL)
    val xb = GivenQuantity("xb", locatedCompositeSection.L / 2)
    val xv = GivenQuantity("xv", 0.0)
    private fun getGivenQuantities(): Set<GivenQuantity> = setOf(
        Asb, Ast, Asw, Ec, Es, Fy, Fyw, L, b, be, bw, d, dj, fc, h, n, s, wsD, xb, xv
    )

    val Delta_aDL = Delta_aDL(this)
    val Delta_aL = Delta_aL(this)
    val Delta_DL = Delta_DL(this)
    val Delta_L = Delta_L(this)
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
    val fn = fn(this)
    val fn_min = fn_min(this)
    val ns = ns(this)
    val phi_b = phi_b(this)
    val phi_v = phi_v(this)
    val q = q(this)
    val qD = qD(this)
    val qL = qL(this)
    val qu = qu(this)
    val w = w(this)
    val wcD = wcD(this)
    val wu = wu(this)
    val wD = wD(this)
    val y_composite = y_composite(this)

    private val ratio_b = ratio_b(this)
    private val ratio_f = ratio_f(this)
    private val ratio_Delta = ratio_Delta(this)
    private val ratio_v = ratio_v(this)
    private fun getEvaluatableQuantities(): Set<EvaluatableQuantity> = setOf(
        Delta_aDL, Delta_aL, Delta_DL, Delta_L, I_composite, Mn, Mp, Mu, Qsb, Qst, Sb, Sc, Tp, Vc,
        Vn, Vs, Vu, a, alpha, epsilon_t, fn, fn_min, ns, phi_b, phi_v, q, qD, qL, qu, w, wcD, wu,
        wD, y_composite, ratio_b, ratio_Delta, ratio_f, ratio_v,
    )

    private fun getAllQuantities() = getGivenQuantities() + getEvaluatableQuantities()
    fun get(quantityName: String): NamedQuantity? = getAllQuantities().find { it.name == quantityName }
}