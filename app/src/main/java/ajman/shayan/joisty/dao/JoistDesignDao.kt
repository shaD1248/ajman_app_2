package ajman.shayan.joisty.dao

import ajman.shayan.joisty.entities.JoistDesign
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface JoistDesignDao {
    @Insert
    fun insert(joistDesign: JoistDesign)

    @Update
    fun update(joistDesign: JoistDesign)

    @Query("SELECT * FROM joist_design")
    fun getAll(): LiveData<List<JoistDesign>>
}