package com.github.vacxe.googleplaycli.actions.images.mapper

import com.github.vacxe.googleplaycli.actions.images.configuration.ImagesDeleteConfiguration
import com.github.vacxe.googleplaycli.actions.images.model.ImagesDeleteModel

class ImagesDeleteMapper {
    fun map(configuration: ImagesDeleteConfiguration): ImagesDeleteModel = ImagesDeleteModel(
            packageName = configuration.packageName,
            imageType = configuration.imageType,
            language = configuration.language,
            imageId = configuration.imageId
    )
}