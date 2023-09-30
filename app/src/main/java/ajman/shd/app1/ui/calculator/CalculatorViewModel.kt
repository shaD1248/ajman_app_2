package ajman.shd.app1.ui.calculator

import ajman.shd.app1.entities.JoistDesign
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CalculatorViewModel(var joistDesign: JoistDesign) : ViewModel() {

    private val _joistDesignLiveData = MutableLiveData<JoistDesign>().apply {
        value = joistDesign
    }
    val joistDesignLiveData: LiveData<JoistDesign> = _joistDesignLiveData
}