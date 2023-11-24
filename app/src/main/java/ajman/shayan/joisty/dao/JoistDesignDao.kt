package ajman.shayan.joisty.dao

import ajman.shayan.joisty.entities.JoistDesign
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface JoistDesignDao {
    @Insert
    fun insert(joistDesign: JoistDesign): Long

    @Update
    fun update(joistDesign: JoistDesign)

    @Delete
    fun delete(joistDesign: JoistDesign)

    @Query("SELECT * FROM joist_design")
    fun getAll(): List<JoistDesign>
}