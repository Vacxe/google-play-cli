package com.github.vacxe.googleplaycli.actions.images.mapper

import com.github.vacxe.googleplaycli.actions.images.configuration.ImagesDeleteAllConfiguration
import com.github.vacxe.googleplaycli.actions.images.model.ImagesDeleteAllModel

class ImagesDeleteAllMapper {
    fun map(configuration: ImagesDeleteAllConfiguration): ImagesDeleteAllModel = ImagesDeleteAllModel(
            packageName = configuration.packageName,
            imageType = configuration.imageType,
            language = configuration.language
    )
}