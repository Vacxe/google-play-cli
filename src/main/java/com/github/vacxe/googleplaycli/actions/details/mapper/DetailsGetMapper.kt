package com.github.vacxe.googleplaycli.actions.details.mapper

import com.github.vacxe.googleplaycli.actions.details.configuration.DetailsGetConfiguration
import com.github.vacxe.googleplaycli.actions.details.model.DetailsGetModel

class DetailsGetMapper {
    fun map(configuration: DetailsGetConfiguration): DetailsGetModel = DetailsGetModel(
            packageName = configuration.packageName
    )
}