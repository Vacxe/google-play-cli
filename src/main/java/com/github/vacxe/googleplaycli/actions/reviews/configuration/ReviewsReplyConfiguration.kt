package com.github.vacxe.googleplaycli.actions.reviews.configuration

import com.github.vacxe.googleplaycli.core.BaseConfiguration
import com.xenomachina.argparser.ArgParser
import com.xenomachina.argparser.default

class ReviewsReplyConfiguration(parser: ArgParser) : BaseConfiguration(parser) {
    val reviewId: String by parser
            .storing("--reviewId", "-r")
            .default { "" }

    val replyText: String by parser
            .storing("--replyText", "-a")
            .default { "" }
}
