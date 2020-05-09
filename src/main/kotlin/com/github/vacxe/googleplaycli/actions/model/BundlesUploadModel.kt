package com.github.vacxe.googleplaycli.actions.model

import java.io.File

data class BundlesUploadModel(val packageName: String, val bundle: File, val ackBundleInstallationWarning: Boolean)