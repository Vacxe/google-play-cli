package com.github.vacxe.googleplaycli.actions.internalappsharingartifacts.configuration

import com.github.vacxe.googleplaycli.core.BaseConfiguration
import com.xenomachina.argparser.ArgParser
import com.xenomachina.argparser.default

class InternalappsharingartifactsUploadbundleConfiguration(parser: ArgParser) : BaseConfiguration(parser) {
    val bundle: String by parser
            .storing("--bundle", "-b", help = "Bundle file path")
            .default { "" }
}
