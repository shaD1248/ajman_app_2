package ajman.shayan.joisty.entities

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "price_list")
@RequiresApi(Build.VERSION_CODES.O)
data class PriceList(@ColumnInfo(name = "price_list_code") var priceListCode: Double) {
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "price_list_id") var priceListId: Long = 0
    var currency: String = "USD"
    var plate: Double = 0.0
    var rebar: Double = 0.0
    var angle: Double = 0.0
    var concrete: Double = 0.0
    var block: Double = 0.0
}
