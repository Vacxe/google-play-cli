package com.github.vacxe.googleplaycli.core

import com.xenomachina.argparser.ArgParser
import com.xenomachina.argparser.default
import java.io.File

abstract class BaseConfiguration (parser: ArgParser){
    val serviceAccountJson: File by parser
            .storing("--key", "-k", help = "service account json file path") { File(this) }
            .default(File(System.getenv("PLAYSTORE_SERVICE_ACCOUNT_JSON") ?: ""))

    val packageName: String by parser
            .storing("--packageName", "-p", help = "Package name (Example: com.my.app)")
            .default { System.getenv("APP_PACKAGE_NAME") ?: "" }
}