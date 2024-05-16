package com.github.vacxe.googleplaycli.actions.model

import java.io.File

class InternalAppSharingArtifactsUploadBundleModel(
    val packageName: String,
    val bundle: File,
    requestParameters: String?
): RequestModel(requestParameters)