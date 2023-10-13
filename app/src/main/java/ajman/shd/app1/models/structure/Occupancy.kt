package ajman.shd.app1.models.structure

enum class Occupancy {
    COMMERCIAL,
    PARKING,
    RESIDENTIAL,
    ROOF;

    fun get_wD(): Double = when (this) {
        COMMERCIAL -> 250.0 * kgf / m2
        PARKING -> 250.0 * kgf / m2
        RESIDENTIAL -> 200.0 * kgf / m2
        ROOF -> 300.0 * kgf / m2
    }

    fun get_wL(): Double = when (this) {
        COMMERCIAL -> 600.0 * kgf / m2
        PARKING -> 300.0 * kgf / m2
        RESIDENTIAL -> 300.0 * kgf / m2
        ROOF -> 150.0 * kgf / m2
    }
}
