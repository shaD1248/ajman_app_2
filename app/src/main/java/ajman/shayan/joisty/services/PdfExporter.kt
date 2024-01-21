package ajman.shayan.joisty.services

import android.content.Context
import android.net.Uri
import com.pspdfkit.document.PdfDocumentLoader
import com.pspdfkit.document.html.HtmlToPdfConverter
import java.io.File

//import org.xhtmlrenderer.pdf.ITextRenderer
//import java.io.FileOutputStream
//import java.io.OutputStream
//
//fun exportHtmlToPdf(htmlContent: String, outputPath: String) {
//    val renderer = ITextRenderer()
//    renderer.setDocumentFromString(htmlContent)
//    renderer.layout()
//
//    val os: OutputStream = FileOutputStream(outputPath)
//    renderer.createPDF(os)
//    os.close()
//}

//import android.os.Build
//import androidx.annotation.RequiresApi
//import org.apache.pdfbox.pdmodel.PDDocument
//import org.apache.pdfbox.pdmodel.PDPage
//import org.apache.pdfbox.pdmodel.PDPageContentStream
//import org.apache.pdfbox.pdmodel.font.PDType1Font
//import org.thymeleaf.TemplateEngine
//import org.thymeleaf.context.Context
//import java.io.ByteArrayOutputStream
//import java.io.File
//import java.io.FileInputStream
//import java.io.FileOutputStream
//import java.io.OutputStreamWriter
//import java.nio.charset.StandardCharsets

//import com.itextpdf.html2pdf.HtmlConverter


//@RequiresApi(Build.VERSION_CODES.TIRAMISU)
fun convertHtmlToPdf(html: String, outputPath: String, context: Context) {
    val dir = File(outputPath, "joisty")
    dir.mkdirs()
    val file = File.createTempFile("export", ".pdf", dir);
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
////    // Create a PDF document
////    try {
////    val document = PDDocument()
////    val page = PDPage()
////    document.addPage(page)
////
////    // Create a content stream to write to the page
////    val contentStream = PDPageContentStream(document, page)
////
////    // Use Thymeleaf to process the HTML content
////    val templateEngine = TemplateEngine()
////    val context = Context()
////    context.setVariable("content", htmlContent)
////    val processedHtml = templateEngine.process("your_template_name.html", context)
////
////    // Use PDFBox to write the HTML content to the PDF
////    val outputStream = ByteArrayOutputStream()
////    val writer = OutputStreamWriter(outputStream, StandardCharsets.UTF_8)
////    writer.use { it.write(processedHtml) }
////
////    contentStream.beginText()
////    contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12f)
////    contentStream.newLineAtOffset(100f, 700f)
////    contentStream.showText(outputStream.toString(StandardCharsets.UTF_8))
////    contentStream.endText()
////
////    // Save the document to a file or perform other actions as needed
////    document.save(File(outputPath))
////    document.close()
////    } catch (e: Exception) {
////        e.printStackTrace()
////    }
//
////    val htmlFilePath = outputPath
////    val pdfFilePath = outputPath
////
////    File(htmlFilePath).writeText(htmlContent)
////    FileInputStream(htmlFilePath).use { htmlInputStream ->
////        FileOutputStream(pdfFilePath).use { pdfOutputStream ->
////            HtmlConverter.convertToPdf(htmlInputStream, pdfOutputStream)
////        }
////    }
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
