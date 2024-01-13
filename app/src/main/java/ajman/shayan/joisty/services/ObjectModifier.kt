package ajman.shayan.joisty.services

fun get(entity: Any, fieldName: String): Any? {
    val field = entity.javaClass.getDeclaredField(fieldName)
    field.isAccessible = true
    return field.get(entity)
}

fun set(entity: Any, fieldName: String, value: Any?) {
    val field = entity.javaClass.getDeclaredField(fieldName)
    field.isAccessible = true
    field.set(entity, value)
}