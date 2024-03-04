package ajman.shayan.joisty.fragments

import ajman.shayan.joisty.JoistyApplication
import ajman.shayan.joisty.R
import ajman.shayan.joisty.adapters.SpinnerAdapter
import ajman.shayan.joisty.databinding.FragmentJoistDesignBinding
import ajman.shayan.joisty.entities.JoistDesign
import ajman.shayan.joisty.entities.PriceList
import ajman.shayan.joisty.models.report.Report
import ajman.shayan.joisty.models.structure.ConcreteGrade
import ajman.shayan.joisty.models.structure.JoistArrangement
import ajman.shayan.joisty.models.structure.Occupancy
import ajman.shayan.joisty.models.structure.SteelSectionDetails
import ajman.shayan.joisty.models.structure.cm
import ajman.shayan.joisty.models.structure.m
import ajman.shayan.joisty.services.HtmlGenerator
import ajman.shayan.joisty.services.JoistDesignService
import ajman.shayan.joisty.services.convertHtmlToPdf
import ajman.shayan.joisty.services.set
import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import java.io.IOException
import java.io.OutputStream
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
class JoistDesignFragment : Fragment() {
    private var _binding: FragmentJoistDesignBinding? = null
    private val binding get() = _binding!!
    private var joistDesign: JoistDesign? = null
    private var priceList: PriceList = PriceList(1.0).apply {}
    private var outputPath: String? = null
    private var additionalFieldsVisible = false
    private var updateView = false

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentJoistDesignBinding.inflate(inflater, container, false)
        setSpinnerValues()
        val joistDesignId = arguments?.getLong("joistDesignId")
        val application = requireActivity().application as JoistyApplication
        application.repo.get(joistDesignId, fun (joistDesign: JoistDesign?) {
            this.joistDesign = joistDesign ?: JoistDesign(600.0).apply {
                projectName = getString(R.string.default_project_name)
            }
            updateView = true
        })
        application.priceListRepo.getOne(fun (priceList: PriceList?) {
            if (priceList != null) this.priceList = priceList
        })
        postDelayed()
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
            spinner.adapter = SpinnerAdapter(requireContext(), values)
        }
    }

    private fun postDelayed() {
        val mainHandler = Handler(Looper.getMainLooper())

        mainHandler.post(object : Runnable {
            override fun run() {
                if (updateView) {
                    joistDesign?.let { convertDataToFrom(it) }
                    updateView = false
                }
                mainHandler.postDelayed(this, 1000)
            }
        })
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.buttonAnalyze).setOnClickListener {
            joistDesign?.let {
                convertFormToData(it)
                convertDataToFrom(it, analyze(it))
            }
        }

        view.findViewById<Button>(R.id.buttonDesign).setOnClickListener {
            joistDesign?.let {
                convertFormToData(it)
                design(it)
                convertDataToFrom(it, analyze(it))
            }
        }

        val contract = ActivityResultContracts.StartActivityForResult()
        val resultLauncher = registerForActivityResult(contract) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                // There are no request codes
                val data: Intent? = result.data
                data?.data?.let { uri ->
                    // Use the URI to create the file
                    val outputStream = requireContext().contentResolver.openOutputStream(uri)
                    outputStream?.use { outputStream ->
                        joistDesign?.let {
                            val html = generateHtml(analyze(it))
                            // Write data to the file if needed
                            writeToOutputStream(outputStream, html)
                        }
                    }
                }
            }
        }
        view.findViewById<Button>(R.id.buttonExportAsPdf).setOnClickListener {
            val intent = Intent(Intent.ACTION_CREATE_DOCUMENT).apply {
                addCategory(Intent.CATEGORY_OPENABLE)
                type = "application/html"
                putExtra(Intent.EXTRA_TITLE, "export.html")
            }
            resultLauncher.launch(intent)
        }

        view.findViewById<TextView>(R.id.textViewShowMore).setOnClickListener {
            val additionalFieldsLayout =
                view.findViewById<LinearLayout>(R.id.layoutAdditionalFields)
            additionalFieldsVisible = !additionalFieldsVisible
            additionalFieldsLayout?.visibility =
                if (additionalFieldsVisible) View.VISIBLE else View.GONE
            view.findViewById<TextView>(R.id.textViewShowMore)?.text =
                requireContext().getString(if (additionalFieldsVisible) R.string.label_show_less else R.string.label_show_more)
        }
    }

    private fun writeToOutputStream(outputStream: OutputStream, text: String) {
        try {
            val bytes = text.toByteArray(Charsets.UTF_8)
            outputStream.write(bytes)
            outputStream.flush()
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            try {
                outputStream.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    private fun export() {
        joistDesign?.let {
            convertHtmlToPdf(generateHtml(analyze(it)), outputPath ?: "", requireContext())
        }
    }

    private fun design(joistDesign: JoistDesign) {
        val joistDesignService = JoistDesignService()
        joistDesignService.design(joistDesign, priceList)
    }

    private fun analyze(joistDesign: JoistDesign): Report {
        val joistDesignService = JoistDesignService()
        return joistDesignService.getAnalysisReport(joistDesign, priceList)
    }

    private fun convertDataToFrom(
        joistDesign: JoistDesign,
        report: Report = Report(listOf(), joistDesign)
    ) {
        val root: View = binding.root
        val textViewFormData = mutableMapOf(
            R.id.textViewId to joistDesign.id.toString(),
            R.id.editTextProjectName to joistDesign.projectName,
            R.id.textViewCreatedAt to formatLocalDateTime(joistDesign.createdAt),
            R.id.textViewUpdatedAt to formatLocalDateTime(joistDesign.updatedAt),
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
        loadWebView(generateHtml(report))
    }

    private fun formatLocalDateTime(localDateTime: LocalDateTime): String {
        val dayOfMonth = localDateTime.dayOfMonth
        val month = localDateTime.month.getDisplayName(TextStyle.SHORT, Locale.getDefault())
        val year = localDateTime.year
        val hour = localDateTime.format(DateTimeFormatter.ofPattern("HH"))
        val minute = localDateTime.format(DateTimeFormatter.ofPattern("mm"))
        return "$dayOfMonth $month $year, $hour:$minute"
    }

    private fun generateHtml(report: Report): String {
        val htmlGenerator = HtmlGenerator(requireContext(), binding.root)
        return htmlGenerator.generateHtml(report)
    }

    private fun loadWebView(html: String) {
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
            set(joistDesign, propertyName, value)
        }

        val application = requireActivity().application as JoistyApplication
        application.repo.run {
            if (joistDesign.id == 0L) insert(joistDesign) else update(joistDesign)
        }
    }
}