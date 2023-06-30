package com.github.vacxe.googleplaycli.actions.model

import java.io.File

class ApksUploadModel(val packageName: String,
                           val editId: String?,
                           val apk: File,
                           parameters: String?
): RequestModel(parameters)
