package com.github.vacxe.googleplaycli.actions.model

import java.io.File

data class ApksUploadModel(val packageName: String, val editId: String?, val apk: File)