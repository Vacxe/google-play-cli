package com.github.vacxe.googleplaycli.actions.reviews.configuration

import com.github.vacxe.googleplaycli.core.BaseConfiguration
import com.xenomachina.argparser.ArgParser
import com.xenomachina.argparser.default

class ReviewsListConfiguration(parser: ArgParser) : BaseConfiguration(parser) {
    val maxResults: Long by parser
            .storing("--maxResults", "-m") { this.toLong()}
            .default { 100L }

    val startIndex: Long by parser
            .storing("--startIndex", "-s") { this.toLong()}
            .default { 0L }

    val translationLanguage: String by parser
            .storing("--translationLanguage", "-l")
            .default { "" }

    val token: String by parser
            .storing("--token", "-t")
            .default { "" }
}
