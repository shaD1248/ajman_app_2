package ajman.shd.app1.ui.calculator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ajman.shd.app1.databinding.FragmentCalculator1Binding

class CalculatorFragment : Fragment() {

    private var _binding: FragmentCalculator1Binding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCalculator1Binding.inflate(inflater, container, false)
        val root: View = binding.root

        _binding?.buttonCalculate?.setOnClickListener {
            // Get input values from EditTexts
            val span = _binding?.editTextSpan?.text.toString().toDoubleOrNull() ?: 0.0
            val load = _binding?.editTextLoad?.text.toString().toDoubleOrNull() ?: 0.0

            // Calculate Shear and Moment
            val shear = 0.5 * span * load
            val moment = (1.0 / 8.0) * Math.pow(span, 2.0) * load

            // Display results in TextViews
            _binding?.textViewShear?.text = "Shear: $shear newtons"
            _binding?.textViewMoment?.text = "Moment: $moment newton-meters"
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}