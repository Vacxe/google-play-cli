package com.github.vacxe.googleplaycli.actions.images.configuration

import com.github.vacxe.googleplaycli.core.BaseConfiguration
import com.xenomachina.argparser.ArgParser
import com.xenomachina.argparser.default

class ImagesUploadConfiguration(parser: ArgParser) : BaseConfiguration(parser) {
    val image: String by parser
            .storing("--image", "-image", help = "Image file path")
            .default { "" }

    val imageType: String by parser
            .storing("--imageType", "-t")
            .default { "" }

    val language: String by parser
            .storing("--language", "-l")
            .default { "" }
}