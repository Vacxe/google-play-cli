package com.github.vacxe.googleplaycli.actions.model

class EditCommitModel(val packageName: String,
                      val editId: String?,
                      val changesNotSentForReview: Boolean = false,
                      requestParameters: String?
): RequestModel(requestParameters)