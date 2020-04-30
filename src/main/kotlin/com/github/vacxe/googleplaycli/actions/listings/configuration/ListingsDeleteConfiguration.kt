package com.github.vacxe.googleplaycli.actions.listings.configuration

import com.github.vacxe.googleplaycli.core.BaseConfiguration
import com.xenomachina.argparser.ArgParser
import com.xenomachina.argparser.default

class ListingsDeleteConfiguration(parser: ArgParser) : BaseConfiguration(parser) {
    val language: String by parser
            .storing("--language", "-l")
            .default { "" }
}