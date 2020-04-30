package com.github.vacxe.googleplaycli.actions.reviews.mapper

import com.github.vacxe.googleplaycli.actions.reviews.configuration.ReviewsGetConfiguration
import com.github.vacxe.googleplaycli.actions.reviews.model.ReviewsGetModel

class ReviewsGetMapper {
    fun map(configuration: ReviewsGetConfiguration): ReviewsGetModel = ReviewsGetModel(
            packageName = configuration.packageName,
            reviewId = configuration.reviewId,
            translationLanguage = configuration.translationLanguage
    )
}