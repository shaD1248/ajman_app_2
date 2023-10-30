package ajman.shayan.joisty.models

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.math.pow


fun solveQuadratic(a: Double, b: Double, c: Double): Double {
    return (-b + (b.pow(2) - 4 * a * c).pow(0.5)) / 2 / a
}

@RequiresApi(Build.VERSION_CODES.O)
fun formatDateTime(dateTime: LocalDateTime): String {
    val today = LocalDateTime.now().toLocalDate()
    val pattern = when {
        dateTime.toLocalDate() == today -> "HH:mm"
        dateTime.year == today.year -> "dd MMM, HH:mm"
        else -> "dd MMM yyyy, HH:mm"
    }
    return dateTime.format(DateTimeFormatter.ofPattern(pattern))
}