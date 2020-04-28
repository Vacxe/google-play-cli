package com.github.vacxe.googleplaycli.actions.tracks.configuration

import com.github.vacxe.googleplaycli.core.BaseConfiguration
import com.xenomachina.argparser.ArgParser
import com.xenomachina.argparser.default

class TracksPatchConfiguration(parser: ArgParser): BaseConfiguration(parser) {
    val track: String by parser
            .storing("--track", "-t", help = "The track to read or modify.")
            .default { "" }

    val apkVersionCode: Int by parser
            .storing("--apkVersionCode", "-v", help = "The version code of the APK whose will be promoted to the track") { this.toInt() }
            .default { 0 }
}
