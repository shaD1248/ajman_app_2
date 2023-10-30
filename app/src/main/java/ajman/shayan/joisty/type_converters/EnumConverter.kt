package ajman.shayan.joisty.type_converters

import androidx.room.TypeConverter

class EnumConverter {
    @TypeConverter
    inline fun <reified T : Enum<T>> fromOrdinal(ordinal: Int?): T? {
        return ordinal?.let {
            enumValues<T>()[it]
        }
    }

    @TypeConverter
    fun toOrdinal(value: Enum<*>?): Int? {
        return value?.ordinal
    }
}
