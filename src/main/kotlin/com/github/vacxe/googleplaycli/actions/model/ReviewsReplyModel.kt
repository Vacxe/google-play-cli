package com.github.vacxe.googleplaycli.actions.model

class ReviewsReplyModel(
    val packageName: String,
    val editId: String?,
    val reviewId: String,
    val replyText: String,
    requestParameters: String?
) : RequestModel(requestParameters)