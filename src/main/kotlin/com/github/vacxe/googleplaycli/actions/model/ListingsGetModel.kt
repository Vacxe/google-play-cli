package com.github.vacxe.googleplaycli.actions.model

class ListingsGetModel(val packageName: String,
                       val editId: String?,
                       val language: String,
                       requestParameters: String?
): RequestModel(requestParameters)