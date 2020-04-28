package com.github.vacxe.googleplaycli.actions.internalappsharingartifacts.mapper

import com.github.vacxe.googleplaycli.actions.internalappsharingartifacts.configuration.InternalappsharingartifactsUploadbundleConfiguration
import com.github.vacxe.googleplaycli.actions.internalappsharingartifacts.model.InternalappsharingartifactsUploadbundleModel
import java.io.File

class InternalappsharingartifactsUploadbundleMapper {
    fun map(configuration: InternalappsharingartifactsUploadbundleConfiguration): InternalappsharingartifactsUploadbundleModel = InternalappsharingartifactsUploadbundleModel(
            packageName = configuration.packageName,
            bundle = File(configuration.bundle)
    )
}