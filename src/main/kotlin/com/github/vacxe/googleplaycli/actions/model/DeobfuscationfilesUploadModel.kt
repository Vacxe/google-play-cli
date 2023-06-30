package com.github.vacxe.googleplaycli.actions.model

import java.io.File

class DeobfuscationfilesUploadModel(
    val packageName: String,
    val editId: String?,
    val apkVersionCode: Int,
    val deobfuscation: File,
    val deobfuscationFileType: String,
    parameters: String?
) : RequestModel(parameters)