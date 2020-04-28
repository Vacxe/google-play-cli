package com.github.vacxe.googleplaycli.actions.bundles.mapper

import com.github.vacxe.googleplaycli.actions.bundles.configuration.BundlesListConfiguration
import com.github.vacxe.googleplaycli.actions.bundles.model.BundlesListModel

class BundlesListMapper {
    fun map(configuration: BundlesListConfiguration): BundlesListModel = BundlesListModel(
            packageName = configuration.packageName
    )
}