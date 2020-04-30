package com.github.vacxe.googleplaycli.actions.internalappsharingartifacts.configuration

import com.github.vacxe.googleplaycli.core.BaseConfiguration
import com.xenomachina.argparser.ArgParser
import com.xenomachina.argparser.default

class InternalappsharingartifactsUploadapkConfiguration(parser: ArgParser) : BaseConfiguration(parser) {
    val apk: String by parser
            .storing("--apk", "-a", help = "Apk file path")
            .default { "" }
}
