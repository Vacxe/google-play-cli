package com.github.vacxe.googleplaycli.actions.apks.mappers

import com.github.vacxe.googleplaycli.actions.apks.configuration.ApksListConfiguration
import com.github.vacxe.googleplaycli.actions.apks.models.ApksListModel
import com.github.vacxe.googleplaycli.actions.bundles.configuration.BundlesListConfiguration
import com.github.vacxe.googleplaycli.actions.bundles.models.BundlesListModel

class ApksListMapper {
    fun map(configuration: ApksListConfiguration): ApksListModel = ApksListModel(
            packageName = configuration.packageName
    )
}