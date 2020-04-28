package com.github.vacxe.googleplaycli.actions.reviews.model

data class ReviewsListModel(val packageName: String,
                            val maxResults: Long,
                            val startIndex: Long,
                            val token: String,
                            val translationLanguage: String)