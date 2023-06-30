package com.github.vacxe.googleplaycli.actions.model

class ImagesDeleteModel(val packageName: String,
                             val editId: String?,
                             val imageType: String,
                             val language: String,
                             val imageId: String,
                             requestParameters: String?
): RequestModel(requestParameters)