package com.github.vacxe.googleplaycli.actions.listings.configuration

import com.github.vacxe.googleplaycli.core.BaseConfiguration
import com.xenomachina.argparser.ArgParser
import com.xenomachina.argparser.default

class ListingsUpdateConfiguration(parser: ArgParser) : BaseConfiguration(parser) {
    val language: String by parser
            .storing("--language", "-l", help = "Language localization code (for example, \"es-419\" for Latin American Spanish).")
            .default { "" }

    val fullDescription: String by parser
            .storing("--fullDescription", "-d", help = "Full description of the app; this may be up to 4000 characters in length.")
            .default { "" }

    val shortDescription: String by parser
            .storing("--shortDescription", "-s", help = "Short description of the app (previously known as promo text); this may be up to 80 characters in length.")
            .default { "" }

    val title: String by parser
            .storing("--title", "-t", help = "App's localized title.")
            .default { "" }

    val video: String by parser
            .storing("--video", "-v", help = "URL of a promotional YouTube video for the app.")
            .default { "" }

}