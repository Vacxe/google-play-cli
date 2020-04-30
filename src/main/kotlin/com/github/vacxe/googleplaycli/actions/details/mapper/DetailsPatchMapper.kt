package com.github.vacxe.googleplaycli.actions.details.mapper

import com.github.vacxe.googleplaycli.actions.details.configuration.DetailsPatchConfiguration
import com.github.vacxe.googleplaycli.actions.details.model.DetailsPatchModel
import com.github.vacxe.googleplaycli.core.nullIfEmpty

class DetailsPatchMapper {
    fun map(configuration: DetailsPatchConfiguration): DetailsPatchModel = DetailsPatchModel(
            packageName = configuration.packageName,
            contactEmail = configuration.contactEmail.nullIfEmpty(),
            contactPhone = configuration.contactPhone.nullIfEmpty(),
            contactWebsite = configuration.contactWebsite.nullIfEmpty(),
            defaultLanguage = configuration.defaultLanguage.nullIfEmpty()
    )
}