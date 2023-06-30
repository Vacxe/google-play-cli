package com.github.vacxe.googleplaycli.actions.model

class ReviewsListModel(val packageName: String,
                            val editId: String?,
                            val maxResults: Long,
                            val startIndex: Long,
                            val token: String,
                            val translationLanguage: String,
                            requestParameters: String?
): RequestModel(requestParameters)