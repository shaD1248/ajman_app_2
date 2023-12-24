package ajman.shayan.joisty.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "loading")
data class Loading(
    @ColumnInfo(name = "label") var label: String,
    @ColumnInfo(name = "including_self_weight") var includingSelfWeight: Boolean,
    @ColumnInfo(name = "wd") var wD: Double,
    @ColumnInfo(name = "wl") var wL: Double,
) {
    @PrimaryKey(autoGenerate = true) var id: Long = 0
}
