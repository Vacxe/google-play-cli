package com.github.vacxe.googleplaycli.actions.bundles.mapper

import com.github.vacxe.googleplaycli.actions.bundles.configuration.BundlesUploadConfiguration
import com.github.vacxe.googleplaycli.actions.bundles.model.BundlesUploadModel
import java.io.File

class BundlesUploadMapper {
    fun map(configuration: BundlesUploadConfiguration): BundlesUploadModel = BundlesUploadModel(
            packageName = configuration.packageName,
            bundle = File(configuration.bundle),
            ackBundleInstallationWarning = configuration.ackBundleInstallationWarning
    )
}