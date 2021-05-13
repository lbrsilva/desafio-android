package br.com.lbrsilva.nasa.helper.transformer

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class YoutubeTramsformerTest {
    @Test
    fun `Extract Id When URL embed`() {
        val youtubeUrl = "https://www.youtube.com/embed/B1R3dTdcpSU?rel=0"
        val value = YoutubeTransformer.extractId(youtubeUrl)

        Assert.assertEquals("B1R3dTdcpSU", value)
    }

    @Test
    fun `Extract Id When URL is from Youtube Web`() {
        val youtubeUrl = "https://www.youtube.com/watch?v=JxzIYbBiRpM"
        val value = YoutubeTransformer.extractId(youtubeUrl)

        Assert.assertEquals("JxzIYbBiRpM", value)
    }
}