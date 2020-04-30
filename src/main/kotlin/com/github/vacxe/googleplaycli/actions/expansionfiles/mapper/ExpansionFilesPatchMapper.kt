package com.github.vacxe.googleplaycli.actions.expansionfiles.mapper

import com.github.vacxe.googleplaycli.actions.expansionfiles.configuration.ExpansionFilesPatchConfiguration
import com.github.vacxe.googleplaycli.actions.expansionfiles.model.ExpansionFilesPatchModel

class ExpansionFilesPatchMapper {
    fun map(configuration: ExpansionFilesPatchConfiguration): ExpansionFilesPatchModel = ExpansionFilesPatchModel(
            packageName = configuration.packageName,
            apkVersionCode = configuration.apkVersionCode,
            expansionFileType = configuration.expansionFileType,
            referencesVersion = configuration.referencesVersion,
            fileSize = configuration.fileSize
    )
}