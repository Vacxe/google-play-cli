package com.github.vacxe.googleplaycli.actions.model

import java.io.File

class InternalappsharingartifactsUploadbundleModel(
    val packageName: String,
    val bundle: File,
    requestParameters: String?
): RequestModel(requestParameters)