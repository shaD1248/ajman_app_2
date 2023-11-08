package ajman.shayan.joisty.models.datasets

import ajman.shayan.joisty.models.quantity_models.EvaluatableQuantity
import ajman.shayan.joisty.models.quantities.a
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
import ajman.shayan.joisty.models.quantities.qu
import ajman.shayan.joisty.models.quantities.ratio_b
import ajman.shayan.joisty.models.quantities.y_composite
import ajman.shayan.joisty.models.quantity_models.NamedQuantity
import ajman.shayan.joisty.models.structure.LocatedCompositeSection

class DataSet(locatedCompositeSection: LocatedCompositeSection) {
    val b = locatedCompositeSection.b
    val Asb = locatedCompositeSection.Asb
    val Ast = locatedCompositeSection.Ast
    val be = locatedCompositeSection.be
    val bw = locatedCompositeSection.bw
    val d = locatedCompositeSection.d
    val dj = locatedCompositeSection.dj
    val Ec = locatedCompositeSection.Ec
    val Es = locatedCompositeSection.Es
    val fc = locatedCompositeSection.fc
    val Fy = locatedCompositeSection.Fy
    val h = locatedCompositeSection.h
    val L = locatedCompositeSection.L
    val wu = locatedCompositeSection.wu
    val x = locatedCompositeSection.x
    val hasConcreteWeb = true

    val a = a(this)
    val epsilon_t = epsilon_t(this)
    val I_composite = I_composite(this)
    val Mn = Mn(this)
    val Mp = Mp(this)
    val Mu = Mu(this)
    val ns = ns(this)
    val phi_b = phi_b(this)
    val Qsb = Qsb(this)
    val Qst = Qst(this)
    val qu = qu(this)
    val ratio_b = ratio_b(this)
    val Sb = Sb(this)
    val Sc = Sc(this)
    val Tp = Tp(this)
    val y_composite = y_composite(this)

    fun getAllEvaluatableQuantities(): MutableSet<EvaluatableQuantity> = mutableSetOf(a, epsilon_t, I_composite, Mn, Mp, Mu, ns, phi_b, Qsb, Qst, qu, ratio_b, Sb, Sc, Tp, y_composite)
    fun get(quantityName: String): NamedQuantity? = getAllEvaluatableQuantities().find { it.name == quantityName }
}