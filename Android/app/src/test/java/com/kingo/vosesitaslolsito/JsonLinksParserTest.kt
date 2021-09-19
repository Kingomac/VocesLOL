package com.kingo.vosesitaslolsito

import com.kingo.vosesitaslolsito.util.JsonLinksParser
import com.kingo.vosesitaslolsito.util.SoundsData
import org.junit.Assert
import org.junit.Test

class JsonLinksParserTest {

    @Test
    fun parseToArray() {

        Assert.assertArrayEquals(
            arrayOf(
                SoundsData("key1", arrayOf("value1", "value2", "value3")),
                SoundsData("key2", arrayOf("value1"))
            ), JsonLinksParser.parseToArray(input)
        )
    }

    @Test
    fun parseToMap() {
        val expected: Map<String, Array<String>> = mapOf(Pair("key1", arrayOf("value1", "value2", "value3")), Pair("key2", arrayOf("value1")))
        val result: Map<String, Array<String>> = JsonLinksParser.parseToMap(input)
        if(expected.size != result.size) Assert.fail("Expected size: ${expected.size} / Result size: ${result.size}")
            // que pereza
        }

    companion object {
        private const val input =
            """
                 {
                    "key1": [
                        "value1",
                        "value2",
                        "value3"
                    ],
                    "key2": [
                        "value1"
                    ]
                 }
                """
    }
}