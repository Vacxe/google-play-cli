package com.github.vacxe.googleplaycli.actions.images.mapper

import com.github.vacxe.googleplaycli.actions.details.configuration.DetailsPatchConfiguration
import com.github.vacxe.googleplaycli.actions.details.model.DetailsPatchModel
import com.github.vacxe.googleplaycli.actions.images.configuration.ImageDeleteAllConfiguration
import com.github.vacxe.googleplaycli.actions.images.configuration.ImageListConfiguration
import com.github.vacxe.googleplaycli.actions.images.model.ImagesDeleteAllModel
import com.github.vacxe.googleplaycli.actions.images.model.ImagesListModel
import com.github.vacxe.googleplaycli.core.nullIfEmpty

class ImageListMapper {
    fun map(configuration: ImageListConfiguration): ImagesListModel = ImagesListModel(
            packageName = configuration.packageName,
            imageType = configuration.imageType,
            language = configuration.language
    )
}