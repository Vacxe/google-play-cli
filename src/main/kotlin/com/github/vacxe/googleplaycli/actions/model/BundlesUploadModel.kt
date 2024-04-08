package com.github.vacxe.googleplaycli.actions.model

import java.io.File

class BundlesUploadModel(
    val packageName: String,
    val editId: String?,
    val bundle: File,
    val ackBundleInstallationWarning: Boolean,
    val deviceTierConfigId: String?,
    parameters: String?
) : RequestModel(parameters)