package com.github.vacxe.googleplaycli.actions.expansionfiles.mapper

import com.github.vacxe.googleplaycli.actions.expansionfiles.configuration.ExpansionFilesGetConfiguration
import com.github.vacxe.googleplaycli.actions.expansionfiles.model.ExpansionFilesGetModel

class ExpansionFilesGetMapper {
    fun map(configuration: ExpansionFilesGetConfiguration): ExpansionFilesGetModel = ExpansionFilesGetModel(
            packageName = configuration.packageName,
            apkVersionCode = configuration.apkVersionCode,
            expansionFileType = configuration.expansionFileType
    )
}