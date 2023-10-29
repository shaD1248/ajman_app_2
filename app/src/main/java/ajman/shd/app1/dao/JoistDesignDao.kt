package ajman.shd.app1.dao

import ajman.shd.app1.entities.JoistDesign
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