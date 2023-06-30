package com.github.vacxe.googleplaycli.core

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.convert
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.validate
import com.github.vacxe.googleplaycli.PlayStoreApi
import java.io.ByteArrayInputStream
import java.io.File
import java.io.FileInputStream
import java.io.InputStream
import java.security.InvalidParameterException

abstract class BaseCommand(name: String, actionDescription: String = "") : CliktCommand(name = name, help = actionDescription) {

    private val serviceAccountJsonFile: String by option("--config-file", "-cf", help = "service account json file path")
        .default(System.getenv("PLAYSTORE_SERVICE_ACCOUNT_JSON_FILE")
            ?: "")

    private val serviceAccountJsonContent: String by option("--config-content", "-cc", help = "service account json content")
            .default(System.getenv("PLAYSTORE_SERVICE_ACCOUNT_JSON_CONTENT")
                    ?: "")

    val packageName: String by option("--package-name", "-p", help = "package name (example: com.my.app)")
            .default(System.getenv("APP_PACKAGE_NAME") ?: "")
            .validate { require(it.isNotEmpty()) { "Please provide a valid $help" } }

    val parameters: String? by option("--parameters", "-params", help = "array of additional parameters in JSON format ex. {\"key1\":\"value1\", \"key2\":\"value2\"}")

    private val serviceAccountInputStream: InputStream
        get() {
            if(serviceAccountJsonFile.isNotEmpty() && serviceAccountJsonContent.isNotEmpty()) {
                throw InvalidParameterException("Service account file or content can't be specified together")
            }

            if(serviceAccountJsonFile.isEmpty() && serviceAccountJsonContent.isEmpty()) {
                throw InvalidParameterException("Service account File or Content need to be specified")
            }

            return when {
                serviceAccountJsonContent.isNotEmpty() -> ByteArrayInputStream(serviceAccountJsonContent.toByteArray())
                serviceAccountJsonFile.isNotEmpty() -> {
                    val serviceAccountFile = File(serviceAccountJsonFile)
                    if(!serviceAccountFile.exists())
                        throw InvalidParameterException("Service account file $serviceAccountJsonFile not exist")
                    FileInputStream(serviceAccountFile)
                }
                else -> throw InvalidParameterException("Service account input stream can't be defined")
            }
        }

    final override fun run() {
        val manager = PlayStoreApi(serviceAccountInputStream, packageName)
        run(manager)?.let {
            println(it)
        }
    }

    abstract fun run(api: PlayStoreApi): Any?
}