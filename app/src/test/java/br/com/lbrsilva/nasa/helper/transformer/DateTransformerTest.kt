package br.com.lbrsilva.nasa.helper.transformer

import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class DateTransformerTest {
    @Test
    fun subtractDaysSuccess() {
        val date = "2021-05-11"
        val value = DateTransformer.subtractDays(date, 10)

        assertEquals(value, "2021-05-01")
    }

    @Test
    fun subtractDaysError() {
        val date = "2021-05-11"
        val value = DateTransformer.subtractDays(date, 12)

        assertEquals(value, "2021-05-01")
    }
}