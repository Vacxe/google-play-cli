package com.github.vacxe.googleplaycli.actions.model

data class ReviewsListModel(val packageName: String,
                            val editId: String?,
                            val maxResults: Long,
                            val startIndex: Long,
                            val token: String,
                            val translationLanguage: String)