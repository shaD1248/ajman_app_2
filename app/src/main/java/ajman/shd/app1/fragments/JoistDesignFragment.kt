package ajman.shd.app1.fragments

import ajman.shd.app1.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ajman.shd.app1.entities.JoistDesign
import ajman.shd.app1.databinding.FragmentJoistDesignBinding
import ajman.shd.app1.models.structure.Occupancy
import android.os.Build
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.annotation.RequiresApi

class JoistDesignFragment : Fragment() {

    private var _binding: FragmentJoistDesignBinding? = null
    private val binding get() = _binding!!

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentJoistDesignBinding.inflate(inflater, container, false)
        val joistDesign: JoistDesign? =
            arguments?.getParcelable("joistDesign", JoistDesign::class.java)
        setSpinnerOccupancyValues()
        setFieldValues(joistDesign)
        return binding.root
    }

    private fun setFieldValues(joistDesign: JoistDesign?) {
        val root: View = binding.root
        val projectNameTextView: TextView = root.findViewById(R.id.editTextProjectName)
        val joistLengthTextView: TextView = root.findViewById(R.id.editTextJoistLength)
        val occupancyTextView: Spinner = root.findViewById(R.id.spinnerOccupancy)

        projectNameTextView.text = joistDesign?.projectName
        joistLengthTextView.text = joistDesign?.joistLength.toString()
        occupancyTextView.setSelection(joistDesign?.occupancy?.ordinal ?: -1)
    }

    private fun setSpinnerOccupancyValues() {
        val spinnerOccupancy: Spinner = binding.root.findViewById(R.id.spinnerOccupancy)
        val occupancyValues = Occupancy.values()
        val adapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, occupancyValues)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerOccupancy.adapter = adapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.buttonAnalyze).setOnClickListener {
            analyzeJoistDesign(it)
        }
    }

    private fun analyzeJoistDesign(view: View) {
        val x = 0
    }
}