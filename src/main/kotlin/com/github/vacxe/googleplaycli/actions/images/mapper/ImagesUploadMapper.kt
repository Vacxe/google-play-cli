package com.github.vacxe.googleplaycli.actions.images.mapper

import com.github.vacxe.googleplaycli.actions.images.configuration.ImagesUploadConfiguration
import com.github.vacxe.googleplaycli.actions.images.model.ImagesUploadModel
import java.io.File

class ImagesUploadMapper {
    fun map(configuration: ImagesUploadConfiguration): ImagesUploadModel = ImagesUploadModel(
            packageName = configuration.packageName,
            imageType = configuration.imageType,
            language = configuration.language,
            image = File(configuration.image)
    )
}