package com.github.vacxe.googleplaycli.actions.model

class ImagesListModel(val packageName: String,
                           val editId: String?,
                           val imageType: String,
                           val language: String,
                           requestParameters: String?
): RequestModel(requestParameters)