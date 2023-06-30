package com.github.vacxe.googleplaycli.actions.model

import java.io.File

class BundlesUploadModel(
    val packageName: String,
    val editId: String?,
    val bundle: File,
    val ackBundleInstallationWarning: Boolean,
    parameters: String?
) : RequestModel(parameters)