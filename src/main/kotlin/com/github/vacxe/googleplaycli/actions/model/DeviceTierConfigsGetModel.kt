package com.github.vacxe.googleplaycli.actions.model

class ReviewsGetModel(val packageName: String,
                           val editId: String?,
                           val reviewId: String,
                           val translationLanguage: String,
                           requestParameters: String?
): RequestModel(requestParameters)