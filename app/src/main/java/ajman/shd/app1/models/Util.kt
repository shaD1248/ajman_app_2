package ajman.shd.app1.models

import kotlin.math.pow


fun solveQuadratic(a: Double, b: Double, c: Double): Double {
    return (-b + (b.pow(2) - 4 * a * c).pow(0.5)) / 2 / a
}