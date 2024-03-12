package ajman.shayan.joisty.services

import ajman.shayan.joisty.R
import android.content.Context
import android.net.Uri
import android.print.PrintAttributes
import android.print.PrintDocumentAdapter
import android.print.PrintManager
import android.webkit.WebView
import android.webkit.WebViewClient
import com.pspdfkit.document.PdfDocumentLoader
import com.pspdfkit.document.html.HtmlToPdfConverter
import java.io.File
import java.io.IOException

fun interface ConversionCallback {
    fun onConversionComplete(pdfData: ByteArray?, error: Throwable?)
}

fun convertHtmlToPdfBinary(html: String, context: Context, callback: ConversionCallback) {
    val tempFile = File.createTempFile("export", ".pdf", context.cacheDir)
    HtmlToPdfConverter.fromHTMLString(context, html)
        .title("Converted document")
        .convertToPdfAsync(tempFile)
        .subscribe({
            val pdfData = try {
                tempFile.readBytes() // Use readBytes to get binary data
            } catch (e: IOException) {
                null // Handle potential IOException
            } finally {
                tempFile.delete() // Delete the temporary file
            }
            // Conversion successful
            callback.onConversionComplete(pdfData, null)
        }, { error ->
            // Conversion failed
            callback.onConversionComplete(null, error)
        })
}

fun convertHtmlToPdf(html: String, outputPath: String, context: Context) {
    val dir = File(outputPath, "joisty")
    dir.mkdirs()
    val file = File.createTempFile("export", ".pdf", dir)
    HtmlToPdfConverter.fromHTMLString(context, html)
        // Configure title for the created document.
        .title("Converted document")
        // Perform the conversion.
        .convertToPdfAsync(file)
        // Subscribe to the conversion result.
        .subscribe({
            // Open and process the converted document.
            val document = PdfDocumentLoader.openDocument(context, Uri.fromFile(file))
        }, {
            val x = 1
            // Handle the error.
        })
}

public fun saveHtmlAsPdf(htmlContent: String, context: Context) {
    val webView = WebView(context)
    webView.webViewClient = object : WebViewClient() {
        override fun onPageFinished(view: WebView, url: String) {
            createPdf(webView, context)
        }
    }
    webView.loadDataWithBaseURL(null, htmlContent, "text/HTML", "UTF-8", null)
}

private fun createPdf(webView: WebView, context: Context) {
    val printManager = context.getSystemService(Context.PRINT_SERVICE) as PrintManager
    val printAdapter: PrintDocumentAdapter = webView.createPrintDocumentAdapter("MyDocument")
    val jobName = context.getString(R.string.app_name) + " Document"
    val attributes = PrintAttributes.Builder()
        .setMediaSize(PrintAttributes.MediaSize.ISO_A4)
        .build()
    printManager.print(jobName, printAdapter, attributes)
}