package com.github.vacxe.googleplaycli.actions.model

class ExpansionFilesGetModel(val packageName: String,
                             val editId: String?,
                             val apkVersionCode: Int,
                             val expansionFileType: String,
                             requestParameters: String?
): RequestModel(requestParameters)