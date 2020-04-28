package com.github.vacxe.googleplaycli.actions.deobfuscationfiles.configuration

import com.github.vacxe.googleplaycli.core.BaseConfiguration
import com.xenomachina.argparser.ArgParser
import com.xenomachina.argparser.default

class DeobfuscationfilesUploadConfiguration(parser: ArgParser) : BaseConfiguration(parser) {
    val deobfuscation: String by parser
            .storing("--deobsuscation", "-d", help = "Deobsuscation file path")
            .default { "" }

    val apkVersionCode: Int by parser
            .storing("--apkVersionCode", "-v", help = "The version code of the APK whose deobfuscation file is being uploaded.") { this.toInt() }
            .default { 0 }

    val deobfuscationFileType: String by parser
            .storing("--deobfuscationFileType", "-t", help = "Acceptable values are: proguard")
            .default { "proguard" }
}
