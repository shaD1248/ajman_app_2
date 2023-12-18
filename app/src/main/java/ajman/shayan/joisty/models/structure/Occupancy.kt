package ajman.shayan.joisty.models.structure

enum class Occupancy {
    COMMERCIAL,
    PARKING,
    RESIDENTIAL,
    ROOF;

    val shouldAddSelfWeight: Boolean get() = true
    val wsD: Double
        get() = when (this) {
            COMMERCIAL -> 250.0 * kgf / m2
            PARKING -> 250.0 * kgf / m2
            RESIDENTIAL -> 200.0 * kgf / m2
            ROOF -> 300.0 * kgf / m2
        }
    val wL: Double
        get() = when (this) {
            COMMERCIAL -> 600.0 * kgf / m2
            PARKING -> 300.0 * kgf / m2
            RESIDENTIAL -> 300.0 * kgf / m2
            ROOF -> 150.0 * kgf / m2
        }
}
