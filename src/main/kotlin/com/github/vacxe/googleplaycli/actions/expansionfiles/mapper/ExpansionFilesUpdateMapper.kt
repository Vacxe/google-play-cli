package com.github.vacxe.googleplaycli.actions.expansionfiles.mapper

import com.github.vacxe.googleplaycli.actions.expansionfiles.configuration.ExpansionFilesUpdateConfiguration
import com.github.vacxe.googleplaycli.actions.expansionfiles.model.ExpansionFilesUpdateModel

class ExpansionFilesUpdateMapper {
    fun map(configuration: ExpansionFilesUpdateConfiguration): ExpansionFilesUpdateModel = ExpansionFilesUpdateModel(
            packageName = configuration.packageName,
            apkVersionCode = configuration.apkVersionCode,
            expansionFileType = configuration.expansionFileType,
            referencesVersion = configuration.referencesVersion,
            fileSize = configuration.fileSize
    )
}