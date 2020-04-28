package com.github.vacxe.googleplaycli.actions.internalappsharingartifacts.mapper

import com.github.vacxe.googleplaycli.actions.internalappsharingartifacts.configuration.InternalappsharingartifactsUploadapkConfiguration
import com.github.vacxe.googleplaycli.actions.internalappsharingartifacts.model.InternalappsharingartifactsUploadapkModel
import java.io.File

class InternalappsharingartifactsUploadapkMapper {
    fun map(configuration: InternalappsharingartifactsUploadapkConfiguration): InternalappsharingartifactsUploadapkModel = InternalappsharingartifactsUploadapkModel(
            packageName = configuration.packageName,
            apk = File(configuration.apk)
    )
}