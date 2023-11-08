package ajman.shayan.joisty.models.quantity_models

import kotlin.math.pow

abstract class AbstractQuantity(var value: Double? = null) {
    operator fun plus(operand: Double): Double = (value ?: 0.0) + operand
    operator fun minus(operand: Double): Double = (value ?: 0.0) - operand
    operator fun times(operand: Double): Double = (value ?: 0.0) * operand
    operator fun div(operand: Double): Double = (value ?: 0.0) / operand
    operator fun div(operand: Int): Double = (value ?: 0.0) / operand
    operator fun plus(operand: AbstractQuantity): Double = this + (operand.value ?: 0.0)
    operator fun minus(operand: AbstractQuantity): Double = this - (operand.value ?: 0.0)
    operator fun times(operand: AbstractQuantity): Double = this * (operand.value ?: 0.0)
    operator fun div(operand: AbstractQuantity): Double = this / (operand.value ?: 0.0)
    operator fun unaryMinus(): Double = -(value ?: 0.0)

    operator fun compareTo(operand: Double): Int = when {
        this - operand > 0.0 -> 1
        this - operand < 0.0 -> -1
        else -> 0
    }

    operator fun compareTo(operand: AbstractQuantity): Int = compareTo(operand.value ?: 0.0)

    fun pow(operand: Int): Double = (value ?: 0.0).pow(operand)
}

operator fun Double.plus(operand: AbstractQuantity) = this + (operand.value ?: 0.0)
operator fun Double.minus(operand: AbstractQuantity) = this - (operand.value ?: 0.0)
operator fun Double.times(operand: AbstractQuantity) = this * (operand.value ?: 0.0)
operator fun Double.div(operand: AbstractQuantity) = this / (operand.value ?: 0.0)
operator fun Int.times(operand: AbstractQuantity) = this * (operand.value ?: 0.0)
operator fun Double.compareTo(operand: AbstractQuantity) = -operand.compareTo(this)