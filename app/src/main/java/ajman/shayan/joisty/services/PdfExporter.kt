package ajman.shayan.joisty.services

import ajman.shayan.joisty.R
import android.content.Context
import android.print.PrintAttributes
import android.print.PrintDocumentAdapter
import android.print.PrintManager
import android.webkit.WebView
import android.webkit.WebViewClient

fun saveHtmlAsPdf(htmlContent: String, context: Context) {
    val webView = WebView(context)
    webView.settings.javaScriptEnabled = true
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