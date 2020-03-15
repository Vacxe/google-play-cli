package com.github.vacxe.apkpublisher.args

import com.xenomachina.argparser.ArgParser
import com.xenomachina.argparser.default
import java.io.File

class UploadApkConfiguration(parser: ArgParser) {
    val serviceAccountJson: File by parser
        .storing("--key", "-k", help = "service account json file path") { File(this) }
        .default(File(System.getenv("PLAYSTORE_SERVICE_ACCOUNT_JSON")?: ""))

    val apk: String by parser
        .storing("--apk", "-a", help = "Apk file path")
        .default { "" }

    val track: String by parser
        .storing("--track", "-t", help = "Track name. By default 'internal'")
        .default { "internal" }

    val obb: String by parser
        .storing("--obb", "-o", help = "Obb file path")
        .default { "" }

    val deobfuscation: String by parser
        .storing("--deobfuscation", "-d", help = "Deobfuscation file path")
        .default { "" }
}