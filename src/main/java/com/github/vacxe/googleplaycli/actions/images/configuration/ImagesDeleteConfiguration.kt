package com.github.vacxe.googleplaycli.actions.images.configuration

import com.github.vacxe.googleplaycli.core.BaseConfiguration
import com.xenomachina.argparser.ArgParser
import com.xenomachina.argparser.default

class ImagesDeleteConfiguration(parser: ArgParser) : BaseConfiguration(parser) {
    val imageId: String by parser
            .storing("--imageId", "-i")
            .default { "" }

    val imageType: String by parser
            .storing("--imageType", "-t")
            .default { "" }

    val language: String by parser
            .storing("--language", "-l")
            .default { "" }
}