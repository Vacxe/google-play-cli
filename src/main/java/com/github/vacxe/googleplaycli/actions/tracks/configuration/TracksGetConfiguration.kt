package com.github.vacxe.googleplaycli.actions.tracks.configuration

import com.github.vacxe.googleplaycli.core.BaseConfiguration
import com.xenomachina.argparser.ArgParser
import com.xenomachina.argparser.default

class TracksGetConfiguration(parser: ArgParser): BaseConfiguration(parser) {
    val track: String by parser
            .storing("--track", "-t", help = "The track to read or modify.")
            .default { "" }
}
