package com.github.vacxe.googleplaycli.actions.model

import java.io.File

class ImagesUploadModel(val packageName: String,
                             val editId: String?,
                             val imageType: String,
                             val language: String,
                             val image: File,
                             requestParameters: String?
): RequestModel(requestParameters)