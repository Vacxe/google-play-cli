package com.github.vacxe.googleplaycli.actions.model

class EditModel(val packageName: String,
                val editId: String?,
                requestParameters: String?
): RequestModel(requestParameters)