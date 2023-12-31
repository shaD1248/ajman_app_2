package ajman.shayan.joisty.dao

import ajman.shayan.joisty.entities.PriceList
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface PriceListDao {
    @Insert
    fun insert(priceList: PriceList): Long

    @Query("SELECT * FROM price_list WHERE price_list_id = :priceListId")
    fun get(priceListId: Long): PriceList?

    @Query("SELECT * FROM price_list")
    fun getOne(): PriceList?

    @Update
    fun update(priceList: PriceList)

    @Delete
    fun delete(priceList: PriceList)

    @Query("SELECT * FROM price_list")
    fun getAll(): List<PriceList>
}
