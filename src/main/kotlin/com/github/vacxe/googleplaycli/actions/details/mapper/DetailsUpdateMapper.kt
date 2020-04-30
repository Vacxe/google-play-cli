package com.github.vacxe.googleplaycli.actions.details.mapper

import com.github.vacxe.googleplaycli.actions.details.configuration.DetailsUpdateConfiguration
import com.github.vacxe.googleplaycli.actions.details.model.DetailsUpdateModel

class DetailsUpdateMapper {
    fun map(configuration: DetailsUpdateConfiguration): DetailsUpdateModel = DetailsUpdateModel(
            packageName = configuration.packageName,
            contactEmail = configuration.contactEmail,
            contactPhone = configuration.contactPhone,
            contactWebsite = configuration.contactWebsite,
            defaultLanguage = configuration.defaultLanguage
    )
}