package com.github.vacxe.googleplaycli.actions.reviews.mapper

import com.github.vacxe.googleplaycli.actions.reviews.configuration.ReviewsListConfiguration
import com.github.vacxe.googleplaycli.actions.reviews.models.ReviewsListModel

class ReviewsListMapper {
    fun map(configuration: ReviewsListConfiguration): ReviewsListModel = ReviewsListModel(
            packageName = configuration.packageName,
            maxResults = configuration.maxResults,
            startIndex = configuration.startIndex,
            token = configuration.token,
            translationLanguage = configuration.translationLanguage
    )
}