package com.github.vacxe.googleplaycli.actions.apks.mapper

import com.github.vacxe.googleplaycli.actions.apks.configuration.ApksUploadConfiguration
import com.github.vacxe.googleplaycli.actions.apks.model.ApksUploadModel
import java.io.File

class ApksUploadMapper {
    fun map(configuration: ApksUploadConfiguration): ApksUploadModel = ApksUploadModel(
            packageName = configuration.packageName,
            apk = File(configuration.apk)
    )
}