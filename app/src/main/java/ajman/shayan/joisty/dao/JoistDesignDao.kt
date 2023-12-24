package ajman.shayan.joisty.dao

import ajman.shayan.joisty.entities.JoistDesign
import ajman.shayan.joisty.entities.Loading
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface JoistDesignDao {
    @Insert
    fun insert(joistDesign: JoistDesign): Long

    @Query("SELECT * " +
            "FROM joist_design j " +
            "LEFT JOIN loading l ON j.loading_id = l.id " +
            "WHERE j.id = :joistDesignId")
    fun get(joistDesignId: Long?): Map<JoistDesign, List<Loading>>

    @Update
    fun update(joistDesign: JoistDesign)

    @Delete
    fun delete(joistDesign: JoistDesign)

    @Query("SELECT * FROM joist_design j LEFT JOIN loading l ON j.loading_id = l.id")
    fun getAll(): Map<JoistDesign, List<Loading>>
}