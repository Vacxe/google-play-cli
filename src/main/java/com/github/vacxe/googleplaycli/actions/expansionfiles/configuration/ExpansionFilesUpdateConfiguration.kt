package com.github.vacxe.googleplaycli.actions.expansionfiles.configuration

import com.github.vacxe.googleplaycli.core.BaseConfiguration
import com.xenomachina.argparser.ArgParser
import com.xenomachina.argparser.default

class ExpansionFilesUpdateConfiguration(parser: ArgParser) : BaseConfiguration(parser) {
    val apkVersionCode: Int by parser
            .storing("--apkVersionCode", "-v") { this.toInt() }
            .default { 0 }

    val expansionFileType: String by parser
            .storing("--expansionFileType", "-t")
            .default { "main" }

    val referencesVersion: Int by parser
            .storing("--referencesVersion", "-r") { this.toInt() }
            .default { 0 }

    val fileSize: Long by parser
            .storing("--fileSize", "-s") { this.toLong() }
            .default { 0L }
}