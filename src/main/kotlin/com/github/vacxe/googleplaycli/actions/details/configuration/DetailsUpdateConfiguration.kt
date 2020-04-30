package com.github.vacxe.googleplaycli.actions.details.configuration

import com.github.vacxe.googleplaycli.core.BaseConfiguration
import com.xenomachina.argparser.ArgParser
import com.xenomachina.argparser.default

class DetailsUpdateConfiguration(parser: ArgParser) : BaseConfiguration(parser) {
    val contactEmail: String by parser
            .storing("--contactEmail", "-e")
            .default { "" }

    val contactPhone: String by parser
            .storing("--contactPhone", "-p")
            .default { "" }

    val contactWebsite: String by parser
            .storing("--contactWebsite", "-w")
            .default { "" }

    val defaultLanguage: String by parser
            .storing("--defaultLanguage", "-l")
            .default { "" }
}