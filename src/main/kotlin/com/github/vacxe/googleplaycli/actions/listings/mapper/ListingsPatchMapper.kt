package com.github.vacxe.googleplaycli.actions.listings.mapper

import com.github.vacxe.googleplaycli.actions.listings.configuration.ListingsPatchConfiguration
import com.github.vacxe.googleplaycli.actions.listings.model.ListingsPatchModel
import com.github.vacxe.googleplaycli.core.nullIfEmpty

class ListingsPatchMapper {
    fun map(configuration: ListingsPatchConfiguration): ListingsPatchModel = ListingsPatchModel(
            packageName = configuration.packageName,
            language = configuration.language,
            fullDescription = configuration.fullDescription.nullIfEmpty(),
            shortDescription = configuration.shortDescription.nullIfEmpty(),
            title = configuration.title.nullIfEmpty(),
            video = configuration.video.nullIfEmpty()
    )
}