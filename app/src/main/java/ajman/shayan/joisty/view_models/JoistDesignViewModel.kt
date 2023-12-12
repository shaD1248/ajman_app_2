package ajman.shayan.joisty.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ajman.shayan.joisty.dao.JoistDesignDao
import ajman.shayan.joisty.entities.JoistDesign
import android.os.Build
import androidx.annotation.RequiresApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class JoistDesignViewModel(private val joistDesignDao: JoistDesignDao) : ViewModel() {
    fun loadAllJoists(updateRecyclerView: (MutableList<JoistDesign>) -> Unit) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val joistDesigns = joistDesignDao.getAll()
                updateRecyclerView(joistDesigns.toMutableList())
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun insert(joistDesign: JoistDesign) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                joistDesign.id = joistDesignDao.insert(joistDesign)
            }
        }
    }

    fun get(id: Long?, handler: (JoistDesign?) -> Unit) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                handler(joistDesignDao.get(id))
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

    fun delete(joistDesign: JoistDesign) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                joistDesignDao.delete(joistDesign)
            }
        }
    }
}
