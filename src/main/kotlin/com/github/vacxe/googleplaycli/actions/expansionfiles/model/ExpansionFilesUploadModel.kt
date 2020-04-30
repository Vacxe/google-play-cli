package com.github.vacxe.googleplaycli.actions.expansionfiles.model

import java.io.File

class ExpansionFilesUploadModel(val packageName: String,
                                val apkVersionCode: Int,
                                val expansionFileType: String,
                                val expansionFile: File)