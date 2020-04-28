package com.github.vacxe.googleplaycli.actions.apks.mappers

import com.github.vacxe.googleplaycli.actions.apks.configuration.ApksUploadConfiguration
import com.github.vacxe.googleplaycli.actions.apks.models.ApksUploadModel
import java.io.File

class ApksUploadMapper {
    fun map(configuration: ApksUploadConfiguration): ApksUploadModel = ApksUploadModel(
            packageName = configuration.packageName,
            apk = File(configuration.apk)
    )
}