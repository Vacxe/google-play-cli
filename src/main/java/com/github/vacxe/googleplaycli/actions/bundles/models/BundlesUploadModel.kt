package com.github.vacxe.googleplaycli.actions.bundles.models

import java.io.File

data class BundlesUploadModel(val packageName: String, val bundle: File, val ackBundleInstallationWarning: Boolean)