package com.github.vacxe.googleplaycli.actions.expansionfiles.mapper

import com.github.vacxe.googleplaycli.actions.expansionfiles.configuration.ExpansionFilesUploadConfiguration
import com.github.vacxe.googleplaycli.actions.expansionfiles.model.ExpansionFilesUploadModel
import java.io.File

class ExpansionFilesUploadMapper {
    fun map(configuration: ExpansionFilesUploadConfiguration): ExpansionFilesUploadModel = ExpansionFilesUploadModel(
            packageName = configuration.packageName,
            apkVersionCode = configuration.apkVersionCode,
            expansionFileType = configuration.expansionFileType,
            expansionFile = File(configuration.expansionFile)
    )
}