package com.github.vacxe.googleplaycli.actions

import com.github.vacxe.googleplaycli.actions.model.ReviewsGetModel
import com.github.vacxe.googleplaycli.actions.model.ReviewsListModel
import com.github.vacxe.googleplaycli.actions.model.ReviewsReplyModel
import com.google.api.services.androidpublisher.model.Review
import com.google.api.services.androidpublisher.model.ReviewsListResponse
import com.google.api.services.androidpublisher.model.ReviewsReplyRequest
import com.google.api.services.androidpublisher.model.ReviewsReplyResponse

interface Reviews : BaseAction {
    fun reviewsList(model: ReviewsListModel): ReviewsListResponse {
        return androidPublisher.Reviews()
                .list(model.packageName)
                .apply {
                    maxResults = model.maxResults
                    startIndex = model.startIndex
                    token = model.token
                    translationLanguage = model.translationLanguage
                }
                .execute()
    }

    fun reviewsGet(model: ReviewsGetModel): Review {
        return androidPublisher.Reviews()
                .get(model.packageName, model.reviewId)
                .apply {
                    translationLanguage = model.translationLanguage
                }
                .execute()
    }

    fun reviewsReply(model: ReviewsReplyModel): ReviewsReplyResponse {
        val reviewsReplyRequest = ReviewsReplyRequest().setReplyText(model.replyText)
        return androidPublisher.Reviews()
                .reply(model.packageName, model.reviewId, reviewsReplyRequest)
                .execute()
    }
}