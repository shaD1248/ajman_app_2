package ajman.shd.app1.enums

import ajman.shd.app1.models.structure.kgf
import ajman.shd.app1.models.structure.m

enum class Unit {
    KGFM, UNIT;

    override fun toString(): String = when (this) {
        KGFM -> "kgfm"
        else -> ""
    }
    fun getValue(): Double = when (this) {
        KGFM -> kgf * m
        UNIT -> 1.0
    }
}