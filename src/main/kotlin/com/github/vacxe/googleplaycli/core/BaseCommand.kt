package com.github.vacxe.googleplaycli.core

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.validate
import com.github.vacxe.googleplaycli.PlayStoreApi
import java.io.File

abstract class BaseCommand(name: String, actionDescription: String = "") : CliktCommand(name = name, help = actionDescription) {
    private val serviceAccountJson: String by option("--config", "-c", help = "service account json file path")
            .default(System.getenv("PLAYSTORE_SERVICE_ACCOUNT_JSON")
                    ?: "")
            .validate { require(it.isNotEmpty()) { "Please provide a valid $help" } }

    val packageName: String by option("--package-name", "-p", help = "package name (example: com.my.app)")
            .default(System.getenv("APP_PACKAGE_NAME") ?: "")
            .validate { require(it.isNotEmpty()) { "Please provide a valid $help" } }

    final override fun run() {
        val manager = PlayStoreApi(File(serviceAccountJson), packageName)
        run(manager)?.let {
            println(it)
        }
    }

    abstract fun run(api: PlayStoreApi): Any?
}