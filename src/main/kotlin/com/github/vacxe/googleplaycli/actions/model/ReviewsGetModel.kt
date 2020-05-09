package com.github.vacxe.googleplaycli.actions.model

data class ReviewsGetModel(val packageName: String,
                           val reviewId: String,
                           val translationLanguage: String)