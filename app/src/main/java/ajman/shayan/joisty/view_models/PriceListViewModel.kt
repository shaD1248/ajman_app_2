package ajman.shayan.joisty.view_models

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ajman.shayan.joisty.dao.PriceListDao
import ajman.shayan.joisty.entities.PriceList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PriceListViewModel(private val priceListDao: PriceListDao) : ViewModel() {

    @RequiresApi(Build.VERSION_CODES.O)
    fun insert(priceList: PriceList) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                priceList.priceListId = priceListDao.insert(priceList)
            }
        }
    }

    fun get(id: Long, handler: (PriceList?) -> Unit) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                handler(priceListDao.get(id))
            }
        }
    }

    fun getOne(handler: (PriceList?) -> Unit) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                handler(priceListDao.getOne())
            }
        }
    }

    fun update(priceList: PriceList) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                priceListDao.update(priceList)
            }
        }
    }

    fun delete(priceList: PriceList) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                priceListDao.delete(priceList)
            }
        }
    }
}
