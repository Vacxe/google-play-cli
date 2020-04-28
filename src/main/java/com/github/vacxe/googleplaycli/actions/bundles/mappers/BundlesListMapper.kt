package com.github.vacxe.googleplaycli.actions.bundles.mappers

import com.github.vacxe.googleplaycli.actions.bundles.configuration.BundlesListConfiguration
import com.github.vacxe.googleplaycli.actions.bundles.models.BundlesListModel

class BundlesListMapper {
    fun map(configuration: BundlesListConfiguration): BundlesListModel = BundlesListModel(
            packageName = configuration.packageName
    )
}