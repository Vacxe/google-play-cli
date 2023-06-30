package com.github.vacxe.googleplaycli

import com.github.vacxe.googleplaycli.actions.model.RequestModel
import org.junit.Test

class RequestModelTest {
    @Test
    fun emptyParametersParseTest() {
        val model = RequestModel(null)
        val parameters = model.parameters

        assert(parameters.isEmpty())
    }

    @Test
    fun singleParametersParseTest() {
        val parametersString = "{\"key1\": \"value1\" }"
        val model = RequestModel(parametersString)
        val parameters = model.parameters

        val expected = mapOf("key1" to "value1")
        assert(parameters == expected)
    }

    @Test
    fun multipleParametersParseTest() {
        val parametersString = "{\"key1\": \"value1\", \"key2\": \"value2\" }"
        val model = RequestModel(parametersString)
        val parameters = model.parameters

        val expected = mapOf("key1" to "value1", "key2" to "value2")
        assert(parameters == expected)
    }
}