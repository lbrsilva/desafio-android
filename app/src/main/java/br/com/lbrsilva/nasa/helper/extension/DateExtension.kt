package br.com.lbrsilva.nasa.helper.extension

import java.text.SimpleDateFormat
import java.util.*

fun Date.format(format: String): String {
    val simpleDateFormat = SimpleDateFormat(format, Locale.getDefault())

    return simpleDateFormat.format(this)
}