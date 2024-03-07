package ajman.shayan.joisty.services

import android.content.Context
import android.net.Uri
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

//import com.itextpdf.text.Document
//import com.itextpdf.text.Element
//import com.itextpdf.text.Image
//import com.itextpdf.text.Paragraph
//import com.itextpdf.text.pdf.PdfWriter
//import org.jlatexmath.renderer.Renderer
//import org.jlatexmath.renderer.awt.AwtGlyphVector
//import org.jlatexmath.renderer.latex.LatexRenderer
//
//import java.awt.image.BufferedImage
//import java.io.ByteArrayOutputStream
//import java.io.FileOutputStream
//import javax.imageio.ImageIO
//
//fun main() {
//    val pdfPath = "output.pdf"
//    convertHtmlToPdf("", pdfPath)
//    println("PDF generated at: $pdfPath")
//}
//
//fun convertHtmlToPdf(htmlContent: String, outputPath: String) {
//    val document = Document()
//    PdfWriter.getInstance(document, FileOutputStream(outputPath))
//    document.open()
//
//    // Add text
//    document.add(Paragraph("Hello, this is some text in the PDF."))
//
//    // Render LaTeX to image
//    val latexExpression = "\\LaTeX"
//    val latexImage = renderLatexToImage(latexExpression)
//
//    // Embed LaTeX image into PDF
//    document.add(Image.getInstance(toByteArray(latexImage)))
//
//    document.close()
//}
//
//fun renderLatexToImage(latexExpression: String): BufferedImage {
//    val latexRenderer: Renderer<BufferedImage> = LatexRenderer.createRenderer()
//    val awtGlyphVector: AwtGlyphVector = latexRenderer.render(latexExpression, 20f, java.awt.Color.BLACK)
//    return awtGlyphVector.getBufferedImage()
//}
//
//fun toByteArray(image: BufferedImage): ByteArray {
//    val baos = ByteArrayOutputStream()
//    ImageIO.write(image, "png", baos)
//    return baos.toByteArray()
//}
