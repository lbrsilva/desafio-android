package br.com.lbrsilva.nasa.helper.transformer

import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class DateTransformerTest {
    @Test
    fun `Success on subtract Days`() {
        val date = "2021-05-11"
        val value = DateTransformer.calculateDays(date, -10)

        assertEquals("2021-05-01", value)
    }

    @Test
    fun `Success on sum Days`() {
        val date = "2021-05-11"
        val value = DateTransformer.calculateDays(date, 1)

        assertEquals("2021-05-12", value)
    }

    @Test
    fun `Error on subtract Days`() {
        val date = "2021-05-11"
        val value = DateTransformer.calculateDays(date, -12)

        assertEquals("2021-05-01", value)
    }

    @Test
    fun `Transform date format`() {
        val date = "2021-05-11"
        val value = DateTransformer.parse(date, "dd/MM/yyyy")

        assertEquals("11/05/2021", value)
    }
}