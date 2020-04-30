package com.github.vacxe.googleplaycli.actions.images.model

data class ImagesDeleteModel(val packageName: String,
                             val imageType: String,
                             val language: String,
                             val imageId: String)