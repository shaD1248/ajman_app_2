package ajman.shayan.joisty.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ajman.shayan.joisty.dao.JoistDesignDao
import ajman.shayan.joisty.entities.JoistDesign
import ajman.shayan.joisty.entities.Loading
import android.os.Build
import androidx.annotation.RequiresApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@RequiresApi(Build.VERSION_CODES.O)
class JoistDesignViewModel(private val joistDesignDao: JoistDesignDao) : ViewModel() {
    fun loadAllJoists(updateRecyclerView: (MutableList<JoistDesign>) -> Unit) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val joistDesigns = relateJoistDesignsLoadings(joistDesignDao.getAll())
                updateRecyclerView(joistDesigns)
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
                val joistDesigns = relateJoistDesignsLoadings(joistDesignDao.get(id))
                handler(joistDesigns.getOrElse(0) { null })
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

    private fun relateJoistDesignsLoadings(map: Map<JoistDesign, List<Loading>>): MutableList<JoistDesign> {
        return map.map { (joistDesign, loadings) ->
            joistDesign.loading = loadings.getOrElse(0) { null }
            joistDesign
        }.toMutableList()
    }
}
