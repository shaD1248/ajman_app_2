package ajman.shayan.joisty.fragments

import ajman.shayan.joisty.JoistyApplication
import ajman.shayan.joisty.R
import ajman.shayan.joisty.databinding.FragmentPriceListBinding
import ajman.shayan.joisty.entities.PriceList
import ajman.shayan.joisty.models.structure.kgf
import ajman.shayan.joisty.models.structure.m3
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment

@RequiresApi(Build.VERSION_CODES.O)
class PriceListFragment : Fragment() {

    private var _binding: FragmentPriceListBinding? = null
    private val binding get() = _binding!!
    private var priceList: PriceList? = null
    private var updateView = false

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPriceListBinding.inflate(inflater, container, false)
//        setSpinnerValues()
        val application = requireActivity().application as JoistyApplication
        application.priceListRepo.getOne(fun (priceList: PriceList?) {
            this.priceList = priceList ?: PriceList(1.0)
            updateView = true
        })
        postDelayed()
        return binding.root
    }

    private fun postDelayed() {
        val mainHandler = Handler(Looper.getMainLooper())

        mainHandler.post(object : Runnable {
            override fun run() {
                if (updateView) {
                    priceList?.let { convertDataToFrom(it) }
                    updateView = false
                }
                mainHandler.postDelayed(this, 1000)
            }
        })
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.btnSave).setOnClickListener {
            priceList?.let {
                convertFormToData(it)
                convertDataToFrom(it)
            }
        }
    }

    private fun convertDataToFrom(priceList: PriceList) {
        val root: View = binding.root
        val textViewFormData = mutableMapOf(
            R.id.editTextPriceListCode to priceList.priceListCode.toString(),
            R.id.editTextCurrency to priceList.currency,
            R.id.editTextPlate to (priceList.plate * kgf).toString(),
            R.id.editTextRebar to (priceList.rebar * kgf).toString(),
            R.id.editTextAngle to (priceList.angle * kgf).toString(),
            R.id.editTextConcrete to (priceList.concrete * m3).toString(),
            R.id.editTextBlock to (priceList.block).toString(),
        )
        for ((viewId, value) in textViewFormData) {
            root.findViewById<TextView?>(viewId).text = value
        }
    }

    private fun convertFormToData(priceList: PriceList) {
        val formData = mutableMapOf(
            "priceList" to binding.editTextPriceListCode.text.toString(),
            "currency" to binding.editTextCurrency.text.toString(),
            "plate" to (binding.editTextPlate.text.toString().toDoubleOrNull() ?: 0.0) / kgf,
            "rebar" to (binding.editTextRebar.text.toString().toDoubleOrNull() ?: 0.0) / kgf,
            "angle" to (binding.editTextAngle.text.toString().toDoubleOrNull() ?: 0.0) / kgf,
            "concrete" to (binding.editTextConcrete.text.toString().toDoubleOrNull() ?: 0.0) / m3,
            "block" to (binding.editTextBlock.text.toString().toDoubleOrNull() ?: 0.0),
        )

        for ((propertyName, value) in formData) {
            val field = priceList.javaClass.getDeclaredField(propertyName)
            field.isAccessible = true
            field.set(priceList, value)
        }

        val application = requireActivity().application as JoistyApplication
        application.priceListRepo.run {
            if (priceList.priceListId == 0L) insert(priceList) else update(priceList)
        }
    }
}