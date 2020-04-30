package com.github.vacxe.googleplaycli.actions.tracks.configuration

import com.github.vacxe.googleplaycli.core.BaseConfiguration
import com.xenomachina.argparser.ArgParser
import com.xenomachina.argparser.default

class TracksPatchConfiguration(parser: ArgParser) : BaseConfiguration(parser) {
    val track: String by parser
            .storing("--track", "-t", help = "The track to read or modify.")
            .default { "" }

    val apkVersionCode: Int by parser
            .storing("--apkVersionCode", "-v", help = "The version code of the APK whose will be promoted to the track") { this.toInt() }
            .default { 0 }

    val userFraction: Double by parser
            .storing("--userFraction", "-f", help = "Fraction of users who are eligible to receive the release. 0 < fraction < 1. To be set, release status must be \"inProgress\" or \"halted\".") { this.toDouble() }
            .default { 1.0 }
}
