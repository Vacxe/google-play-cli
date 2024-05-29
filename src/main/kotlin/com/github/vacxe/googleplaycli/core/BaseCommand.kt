package com.github.vacxe.googleplaycli.core

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.validate
import com.github.vacxe.googleplaycli.PlayStoreApi
import com.github.vacxe.googleplaycli.environments.Env
import com.google.api.client.googleapis.json.GoogleJsonResponseException
import java.io.ByteArrayInputStream
import java.io.File
import java.io.FileInputStream
import java.io.InputStream
import java.security.InvalidParameterException
import kotlin.system.exitProcess

abstract class BaseCommand(name: String, actionDescription: String = "") :
    CliktCommand(name = name, help = actionDescription) {

    private val serviceAccountJsonFile: String by option(
        "--config-file",
        "-cf",
        help = "service account json file path"
    )
        .default(Env.serviceAccoutJsonFile)

    private val serviceAccountJsonContent: String by option(
        "--config-content",
        "-cc",
        help = "service account json content"
    )
        .default(Env.serviceAccoutJsonContent)

    val packageName: String by option("--package-name", "-p", help = "package name (example: com.my.app)")
        .default(Env.packageName)
        .validate { require(it.isNotEmpty()) { "Please provide a valid $help" } }

    private val debug: String by option("--debug", help = "enable debug logs")
        .default("false")

    val parameters: String? by option("--parameters", help = "array of additional parameters in JSON format ex. {\"key1\":\"value1\", \"key2\":\"value2\"}")

    private val serviceAccountInputStream: InputStream
        get() {
            if (serviceAccountJsonFile.isNotEmpty() && serviceAccountJsonContent.isNotEmpty()) {
                throw InvalidParameterException("Service account file or content can't be specified together")
            }

            if (serviceAccountJsonFile.isEmpty() && serviceAccountJsonContent.isEmpty()) {
                throw InvalidParameterException("Service account File or Content need to be specified")
            }

            return when {
                serviceAccountJsonContent.isNotEmpty() -> ByteArrayInputStream(serviceAccountJsonContent.toByteArray())
                serviceAccountJsonFile.isNotEmpty() -> {
                    val serviceAccountFile = File(serviceAccountJsonFile)
                    if (!serviceAccountFile.exists())
                        throw InvalidParameterException("Service account file $serviceAccountJsonFile not exist")
                    FileInputStream(serviceAccountFile)
                }

                else -> throw InvalidParameterException("Service account input stream can't be defined")
            }
        }

    final override fun run() {
        val manager = PlayStoreApi(serviceAccountInputStream, packageName)
        try {
            run(manager)?.let {
                println(it)
            }
        } catch (e: GoogleJsonResponseException) {
            if (debug.toBoolean()) {
                e.printStackTrace()
            } else {
                println(e.content)
            }
            exitProcess(ExitCode.PLAY_CONSOLE_ERROR)
        } catch (e: Exception) {
            println(e)
            exitProcess(ExitCode.DEFAULT)
        }
    }

    abstract fun run(api: PlayStoreApi): Any?

    private companion object {
        object ExitCode {
            const val DEFAULT = 1
            const val PLAY_CONSOLE_ERROR = 2
        }
    }
}