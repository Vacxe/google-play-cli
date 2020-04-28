package com.github.vacxe.googleplaycli.actions.apks.mapper

import com.github.vacxe.googleplaycli.actions.apks.configuration.ApksListConfiguration
import com.github.vacxe.googleplaycli.actions.apks.model.ApksListModel

class ApksListMapper {
    fun map(configuration: ApksListConfiguration): ApksListModel = ApksListModel(
            packageName = configuration.packageName
    )
}