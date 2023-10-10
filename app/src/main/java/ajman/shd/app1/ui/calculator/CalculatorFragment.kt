package ajman.shd.app1.ui.calculator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import ajman.shd.app1.databinding.FragmentCalculator1Binding
import ajman.shd.app1.entities.JoistDesign
import ajman.shd.app1.models.structure.kgf
import ajman.shd.app1.models.structure.m
import ajman.shd.app1.models.structure.m2
import android.webkit.WebView

class CalculatorFragment : Fragment() {
    private var joistDesign = JoistDesign(0.0, 0.0)
    private var _binding: FragmentCalculator1Binding? = null
    private var textViewMap = mutableMapOf<String, TextView>()
    private var webView: WebView? = null

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
        calculatorViewModel.joistDesignLiveData.observe(viewLifecycleOwner, getObserver())
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val items = arrayOf("ratio", "details")
        for (item in items) {
            val textView = TextView(requireContext())
            textView.text = ""
            textView.textSize = 18f
            binding.containerLayout.addView(textView)
            textViewMap[item] = textView
        }

        webView = WebView(requireContext())
        binding.containerLayout.addView(webView)

        binding.buttonCalculate.setOnClickListener {
            joistDesign.span = (binding.editTextSpan.text.toString().toDoubleOrNull() ?: 0.0) * m
            joistDesign.load = (binding.editTextLoad.text.toString().toDoubleOrNull() ?: 0.0) * kgf / m2
            joistDesign.analyze()
            getObserver()(joistDesign)
        }
    }

    private fun getObserver(): (JoistDesign) -> Unit = {
        val spanTextView: TextView = binding.editTextSpan
        val loadTextView: TextView = binding.editTextLoad
        spanTextView.text = (it.span / m).toString()
        loadTextView.text = (it.load / (kgf / m2)).toString()
        val displayItems = mutableMapOf(
            "ratio" to String.format("Ratio: %.2f", it.requirementApplication.ratio),
//            "details" to String.format("Details:\n%s", it.requirementApplication.message)
        )
        for ((key, textView) in textViewMap) {
            textView.text = displayItems[key]
        }
        renderLaTeX(it.requirementApplication.latexLines)
    }

    private fun renderLaTeX(latexLines: MutableList<String>) {
        val document = latexLines.joinToString("\\\\") { latexLine ->
            "\\displaystyle $latexLine"
        }
        webView?.settings?.javaScriptEnabled = true
        val html = "<!DOCTYPE html><html lang=\"en\"><head><script type=\"text/x-mathjax-config\">MathJax.Hub.Config({displayAlign:\"left\"});</script><script type=\"text/javascript\" async src=\"file:///android_asset/mathjax/Mathjax.js?config=TeX-AMS_CHTML\"></script></head><body>$$\\begin{array}{l}${document}\\end{array}$$</body></html>"
//        val html = "<html><head><script type='text/javascript' src='file:///android_asset/mathjax/Mathjax.js'></script></head><body>${document}</body></html>"
        webView?.loadDataWithBaseURL("file:///android_asset/", html, "text/html", "utf-8", null)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}