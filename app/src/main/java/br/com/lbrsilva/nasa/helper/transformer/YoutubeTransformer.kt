package br.com.lbrsilva.nasa.helper.transformer

import java.util.regex.Pattern

object YoutubeTransformer {
    fun extractId(youtubeUrl: String?): String? {
        youtubeUrl?.let {
            val pattern = Pattern.compile("(?<=v=|v\\/|embed\\/|be\\/)([^&?]+)")
            val matcher = pattern.matcher(it)

            if (matcher.find()) {
                return matcher.group(0)
            }
        }

        return youtubeUrl
    }
}