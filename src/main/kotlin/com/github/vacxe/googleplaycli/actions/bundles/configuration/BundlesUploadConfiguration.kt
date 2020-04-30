package com.github.vacxe.googleplaycli.actions.bundles.configuration

import com.github.vacxe.googleplaycli.core.BaseConfiguration
import com.xenomachina.argparser.ArgParser
import com.xenomachina.argparser.default

class BundlesUploadConfiguration(parser: ArgParser) : BaseConfiguration(parser) {
    val bundle: String by parser
            .storing("--bundle", "-b", help = "Bundle file path")
            .default { "" }

    val ackBundleInstallationWarning: Boolean by parser
            .storing("--ackBundleInstallationWarning", "-a", help = "Must be set to true if the bundle installation may trigger a warning on user devices (for example, if installation size may be over a threshold, typically 100 MB).") { this.toBoolean() }
            .default { false }
}
