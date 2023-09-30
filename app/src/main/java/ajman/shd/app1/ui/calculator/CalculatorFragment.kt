package ajman.shd.app1.ui.calculator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import ajman.shd.app1.databinding.FragmentCalculator1Binding
import ajman.shd.app1.entities.JoistDesign

class CalculatorFragment : Fragment() {
    private var joistDesign = JoistDesign(0.0, 0.0)
    private var _binding: FragmentCalculator1Binding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val calculatorViewModel = CalculatorViewModel(joistDesign)

        _binding = FragmentCalculator1Binding.inflate(inflater, container, false)
        val root: View = binding.root

        val spanTextView: TextView = binding.editTextSpan
        val loadTextView: TextView = binding.editTextLoad
        val observer: (value: JoistDesign) -> Unit = {
            spanTextView.text = it.span.toString()
            loadTextView.text = it.load.toString()
            binding.textViewShear.text = String.format("Shear: %s kgf", it.shear)
            binding.textViewMoment.text = String.format("Moment: %s kgfm", it.moment)
        }
        calculatorViewModel.joistDesignLiveData.observe(viewLifecycleOwner, observer)

        binding.buttonCalculate.setOnClickListener {
            joistDesign.span = spanTextView.text.toString().toDoubleOrNull() ?: 0.0
            joistDesign.load = loadTextView.text.toString().toDoubleOrNull() ?: 0.0
            joistDesign.analyze()
            observer(joistDesign)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}