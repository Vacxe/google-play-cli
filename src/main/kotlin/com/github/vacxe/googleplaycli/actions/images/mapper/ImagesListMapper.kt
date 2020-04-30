package com.github.vacxe.googleplaycli.actions.images.mapper

import com.github.vacxe.googleplaycli.actions.images.configuration.ImagesListConfiguration
import com.github.vacxe.googleplaycli.actions.images.model.ImagesListModel

class ImagesListMapper {
    fun map(configuration: ImagesListConfiguration): ImagesListModel = ImagesListModel(
            packageName = configuration.packageName,
            imageType = configuration.imageType,
            language = configuration.language
    )
}