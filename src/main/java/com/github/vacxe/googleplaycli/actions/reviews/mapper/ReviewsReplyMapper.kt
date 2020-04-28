package com.github.vacxe.googleplaycli.actions.reviews.mapper

import com.github.vacxe.googleplaycli.actions.reviews.configuration.ReviewsReplyConfiguration
import com.github.vacxe.googleplaycli.actions.reviews.model.ReviewsReplyModel

class ReviewsReplyMapper {
    fun map(configuration: ReviewsReplyConfiguration): ReviewsReplyModel = ReviewsReplyModel(
            packageName = configuration.packageName,
            reviewId = configuration.reviewId,
            replyText = configuration.replyText
    )
}