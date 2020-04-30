package com.github.vacxe.googleplaycli.actions.listings.mapper

import com.github.vacxe.googleplaycli.actions.listings.configuration.ListingsUpdateConfiguration
import com.github.vacxe.googleplaycli.actions.listings.model.ListingsUpdateModel

class ListingsUpdateMapper {
    fun map(configuration: ListingsUpdateConfiguration): ListingsUpdateModel = ListingsUpdateModel(
            packageName = configuration.packageName,
            language = configuration.language,
            fullDescription = configuration.fullDescription,
            shortDescription = configuration.shortDescription,
            title = configuration.title,
            video = configuration.video
    )
}