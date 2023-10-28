package ajman.shd.app1.fragments

import ajman.shd.app1.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ajman.shd.app1.entities.JoistDesign
import ajman.shd.app1.databinding.FragmentJoistDesignBinding
import ajman.shd.app1.models.structure.ConcreteGrade
import ajman.shd.app1.models.structure.SteelSectionDetails
import ajman.shd.app1.models.structure.JoistArrangement
import ajman.shd.app1.models.structure.Occupancy
import ajman.shd.app1.models.structure.cm
import ajman.shd.app1.models.structure.m
import android.os.Build
import android.webkit.WebView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.TextView
import androidx.annotation.RequiresApi

class JoistDesignFragment : Fragment() {

    private var _binding: FragmentJoistDesignBinding? = null
    private val binding get() = _binding!!
    private var joistDesign: JoistDesign? = null
    private var additionalFieldsVisible = false

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentJoistDesignBinding.inflate(inflater, container, false)
        joistDesign = arguments?.getParcelable("joistDesign", JoistDesign::class.java)
        setSpinnerValues()
        joistDesign?.let { convertDataToFrom(it) }
        return binding.root
    }

    private fun setSpinnerValues() {
        val spinnerSetups = mutableMapOf(
            R.id.spinnerOccupancy to Occupancy.values(),
            R.id.spinnerSteelSectionDetails to SteelSectionDetails.values(),
            R.id.spinnerJoistArrangement to JoistArrangement.values(),
            R.id.spinnerConcreteGrade to ConcreteGrade.values()
        )

        for ((viewId, values) in spinnerSetups) {
            val spinner: Spinner = binding.root.findViewById(viewId)
            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, values)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
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
            val additionalFieldsLayout =
                view.findViewById<LinearLayout>(R.id.layoutAdditionalFields)
            additionalFieldsLayout?.visibility = View.GONE
            additionalFieldsVisible = !additionalFieldsVisible
            additionalFieldsLayout?.visibility =
                if (additionalFieldsVisible) View.VISIBLE else View.GONE
            val showMoreText = if (additionalFieldsVisible) "Show Less" else "Show More"
            view.findViewById<TextView>(R.id.textViewShowMore)?.text = showMoreText
        }
    }

    private fun convertDataToFrom(joistDesign: JoistDesign) {
        val root: View = binding.root
        val textViewFormData = mutableMapOf(
            R.id.editTextProjectName to joistDesign.projectName,
            R.id.editTextJoistLength to (joistDesign.L / m).toString(),
            R.id.editTextJoistHeight to (joistDesign.dj / cm).toString(),
            R.id.editTextSlabThickness to (joistDesign.h / cm).toString(),
            R.id.editTextCeilingDepth to (joistDesign.d / cm).toString(),
        )
//        val checkboxFormData = mutableMapOf(
//            R.id.checkboxConcreteWeb to joistDesign.hasConcreteWeb,
//        )
        val spinnerFormData = mutableMapOf(
            R.id.spinnerOccupancy to joistDesign.occupancy,
            R.id.spinnerSteelSectionDetails to joistDesign.steelSectionDetails,
            R.id.spinnerJoistArrangement to joistDesign.joistArrangement,
            R.id.spinnerConcreteGrade to joistDesign.concreteGrade,
        )
        for ((viewId, value) in textViewFormData) {
            root.findViewById<TextView?>(viewId).text = value
        }
//        for ((viewId, value) in checkboxFormData) {
//            root.findViewById<CheckBox?>(viewId).isChecked = value
//        }
        for ((viewId, value) in spinnerFormData) {
            root.findViewById<Spinner?>(viewId).setSelection(value.ordinal)
        }
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
        val formData = mutableMapOf(
            "projectName" to binding.editTextProjectName.text.toString(),
            "L" to (binding.editTextJoistLength.text.toString().toDoubleOrNull() ?: 0.0) * m,
            "dj" to (binding.editTextJoistHeight.text.toString().toDoubleOrNull() ?: 0.0) * cm,
            "h" to (binding.editTextSlabThickness.text.toString().toDoubleOrNull() ?: 0.0) * cm,
            "d" to (binding.editTextCeilingDepth.text.toString().toDoubleOrNull() ?: 0.0) * cm,
//            "hasConcreteWeb" to binding.checkboxConcreteWeb.isChecked,
            "occupancy" to binding.spinnerOccupancy.selectedItem as Occupancy,
            "steelSectionDetails" to binding.spinnerSteelSectionDetails.selectedItem as SteelSectionDetails,
            "joistArrangement" to binding.spinnerJoistArrangement.selectedItem as JoistArrangement,
            "concreteGrade" to binding.spinnerConcreteGrade.selectedItem as ConcreteGrade,
        )

        for ((propertyName, value) in formData) {
            val field = joistDesign.javaClass.getDeclaredField(propertyName)
            field.isAccessible = true
            field.set(joistDesign, value)
        }
    }
}