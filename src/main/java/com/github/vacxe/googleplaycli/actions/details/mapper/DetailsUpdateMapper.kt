package com.github.vacxe.googleplaycli.actions.details.mapper

import com.github.vacxe.googleplaycli.actions.details.configuration.DetailsUpdateConfiguration
import com.github.vacxe.googleplaycli.actions.details.model.DetailsUpdateModel
import com.github.vacxe.googleplaycli.core.nullIfEmpty

class DetailsUpdateMapper {
    fun map(configuration: DetailsUpdateConfiguration): DetailsUpdateModel = DetailsUpdateModel(
            packageName = configuration.packageName,
            contactEmail = configuration.contactEmail.nullIfEmpty(),
            contactPhone = configuration.contactPhone.nullIfEmpty(),
            contactWebsite = configuration.contactWebsite.nullIfEmpty(),
            defaultLanguage = configuration.defaultLanguage.nullIfEmpty()
    )
}