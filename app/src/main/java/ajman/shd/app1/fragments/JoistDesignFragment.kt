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
import ajman.shd.app1.models.structure.m
import android.os.Build
import android.webkit.WebView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.annotation.RequiresApi

class JoistDesignFragment : Fragment() {

    private var _binding: FragmentJoistDesignBinding? = null
    private val binding get() = _binding!!
    private var joistDesign: JoistDesign? = null

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentJoistDesignBinding.inflate(inflater, container, false)
        joistDesign = arguments?.getParcelable("joistDesign", JoistDesign::class.java)
        setSpinnerOccupancyValues()
        joistDesign?.let { convertDataToFrom(it) }
        return binding.root
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
            joistDesign?.let {
                convertFormToData(it)
                it.analyze()
                convertDataToFrom(it)
            }
        }
        view.findViewById<TextView>(R.id.textViewShowMore).setOnClickListener {
//            joistDesign?.let {
//                convertFormToData(it)
//                it.analyze()
//                convertDataToFrom(it)
//            }
        }
    }

    private fun convertDataToFrom(joistDesign: JoistDesign) {
        val root: View = binding.root
        root.findViewById<TextView?>(R.id.editTextProjectName).text = joistDesign.projectName
        root.findViewById<TextView?>(R.id.editTextJoistLength).text =
            (joistDesign.L / m).toString()
        root.findViewById<Spinner?>(R.id.spinnerOccupancy)
            .setSelection(joistDesign.occupancy.ordinal)
        renderLaTeX(joistDesign.requirementApplication.latexLines)
    }

    private fun renderLaTeX(latexLines: MutableList<String>) {
        val document = latexLines.joinToString("\\\\") { latexLine ->
            "\\displaystyle $latexLine"
        }
        val html =
            "<!DOCTYPE html><html lang=\"en\"><head><script type=\"text/x-mathjax-config\">MathJax.Hub.Config({displayAlign:\"left\"});</script><script type=\"text/javascript\" async src=\"file:///android_asset/mathjax/Mathjax.js?config=TeX-AMS_CHTML\"></script></head><body>$$\\begin{array}{l}${document}\\end{array}$$</body></html>"
        val webView = binding.root.findViewById<WebView>(R.id.webViewResultTable)
        webView?.settings?.javaScriptEnabled = true
        webView?.loadDataWithBaseURL("file:///android_asset/", html, "text/html", "utf-8", null)
    }

    private fun convertFormToData(joistDesign: JoistDesign) {
        joistDesign.projectName = binding.editTextProjectName.text.toString()
        joistDesign.L =
            (binding.editTextJoistLength.text.toString().toDoubleOrNull() ?: 0.0) * m
        joistDesign.occupancy = binding.spinnerOccupancy.selectedItem as Occupancy
    }
}