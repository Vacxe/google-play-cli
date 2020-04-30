package com.github.vacxe.googleplaycli.actions.listings.mapper

import com.github.vacxe.googleplaycli.actions.listings.configuration.ListingsGetConfiguration
import com.github.vacxe.googleplaycli.actions.listings.model.ListingsGetModel

class ListingsGetMapper {
    fun map(configuration: ListingsGetConfiguration): ListingsGetModel = ListingsGetModel(
            packageName = configuration.packageName,
            language = configuration.language
    )
}