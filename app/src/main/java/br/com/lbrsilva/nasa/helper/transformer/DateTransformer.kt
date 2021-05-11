package br.com.lbrsilva.nasa.helper.transformer

import java.text.SimpleDateFormat
import java.util.*

object DateTransformer {
    fun subtractDays(date: String, subDays: Int = 10): String {
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val calendar = Calendar.getInstance()
        calendar.time = formatter.parse(date) ?: Date()
        calendar.add(Calendar.DATE, -subDays)

        return formatter.format(calendar.time)
    }
}