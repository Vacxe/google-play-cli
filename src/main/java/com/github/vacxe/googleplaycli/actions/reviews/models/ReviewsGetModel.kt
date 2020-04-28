package com.github.vacxe.googleplaycli.actions.reviews.models

import java.io.File

data class ReviewsGetModel(val packageName: String,
                           val reviewId: String,
                           val translationLanguage: String)