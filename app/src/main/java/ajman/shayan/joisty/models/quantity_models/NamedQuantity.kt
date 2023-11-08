package ajman.shayan.joisty.models.quantity_models

abstract class NamedQuantity(value: Double? = null) : AbstractQuantity(value) {
    abstract val name: String
}