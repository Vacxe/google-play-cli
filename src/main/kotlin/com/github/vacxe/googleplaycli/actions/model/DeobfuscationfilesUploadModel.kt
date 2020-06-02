package com.github.vacxe.googleplaycli.actions.model

import java.io.File

data class DeobfuscationfilesUploadModel(val packageName: String,
                                         val editId: String?,
                                         val apkVersionCode: Int,
                                         val deobfuscation: File,
                                         val deobfuscationFileType: String)