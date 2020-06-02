package com.github.vacxe.googleplaycli.actions.model

import java.io.File

data class ImagesUploadModel(val packageName: String,
                             val editId: String?,
                             val imageType: String,
                             val language: String,
                             val image: File)