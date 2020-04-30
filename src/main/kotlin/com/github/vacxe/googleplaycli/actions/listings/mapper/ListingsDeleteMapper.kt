package com.github.vacxe.googleplaycli.actions.listings.mapper

import com.github.vacxe.googleplaycli.actions.listings.configuration.ListingsDeleteConfiguration
import com.github.vacxe.googleplaycli.actions.listings.model.ListingsDeleteModel

class ListingsDeleteMapper {
    fun map(configuration: ListingsDeleteConfiguration): ListingsDeleteModel = ListingsDeleteModel(
            packageName = configuration.packageName,
            language = configuration.language
    )
}