package com.github.vacxe.googleplaycli.actions.model

import java.io.File

class InternalAppSharingArtifactsUploadApkModel(
    val packageName: String,
    val apk: File,
    requestParameters: String?
): RequestModel(requestParameters)