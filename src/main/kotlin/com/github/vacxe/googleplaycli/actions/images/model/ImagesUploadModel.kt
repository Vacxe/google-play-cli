package com.github.vacxe.googleplaycli.actions.images.model

import java.io.File

data class ImagesUploadModel(val packageName: String,
                             val imageType: String,
                             val language: String,
                             val image: File)