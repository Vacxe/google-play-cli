package com.github.vacxe.googleplaycli.actions.model

import java.io.File

class ExpansionFilesUploadModel(val packageName: String,
                                val editId: String?,
                                val apkVersionCode: Int,
                                val expansionFileType: String,
                                val expansionFile: File)