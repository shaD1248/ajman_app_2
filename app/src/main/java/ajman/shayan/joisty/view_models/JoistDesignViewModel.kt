package ajman.shayan.joisty.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ajman.shayan.joisty.dao.JoistDesignDao
import ajman.shayan.joisty.entities.JoistDesign
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class JoistDesignViewModel(private val joistDesignDao: JoistDesignDao) : ViewModel() {

    val allJoistDesigns: LiveData<List<JoistDesign>> = joistDesignDao.getAll()

    fun insert(joistDesign: JoistDesign) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                joistDesignDao.insert(joistDesign)
            }
        }
    }

    fun update(joistDesign: JoistDesign) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                joistDesignDao.update(joistDesign)
            }
        }
    }
}
