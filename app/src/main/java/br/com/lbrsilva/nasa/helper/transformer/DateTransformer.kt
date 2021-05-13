package br.com.lbrsilva.nasa.helper.transformer

import java.text.SimpleDateFormat
import java.util.*

object DateTransformer {
    fun calculateDays(date: String, days: Int): String {
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val calendar = Calendar.getInstance()
        calendar.time = formatter.parse(date) ?: Date()
        calendar.add(Calendar.DATE, days)

        return formatter.format(calendar.time)
    }

    fun parse(strDate: String, newFormat: String): String {
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

        formatter.parse(strDate)?.let { date ->
            val newFormatter = SimpleDateFormat(newFormat, Locale.getDefault())

            return newFormatter.format(date)
        }

        return strDate
    }
}