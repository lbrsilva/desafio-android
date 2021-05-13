package br.com.lbrsilva.nasa.helper.extension

import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.text.SimpleDateFormat
import java.util.*

@RunWith(JUnit4::class)
class DateExtensionTest {
    @Test
    fun format() {
        val strDate = "1992-04-06"
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = simpleDateFormat.parse(strDate) ?: Date()

        assertEquals(date.format("yyyy-MM-dd"), strDate)
    }
}