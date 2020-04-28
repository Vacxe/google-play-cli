package com.github.vacxe.googleplaycli.actions.deobfuscationfiles.model

import java.io.File

data class DeobfuscationfilesUploadModel(val packageName: String, val apkVersionCode: Int, val deobfuscation: File, val deobfuscationFileType: String)