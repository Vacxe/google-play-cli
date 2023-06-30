package com.github.vacxe.googleplaycli.actions.model

class ExpansionFilesPatchModel(val packageName: String,
                               val editId: String?,
                               val apkVersionCode: Int,
                               val expansionFileType: String,
                               val referencesVersion: Int?,
                               val fileSize: Long?,
                               requestParameters: String?
): RequestModel(requestParameters)