package com.github.vacxe.apkpublisher.models

import java.io.File

data class UploadApkModel(val apk: File,
                          val track: String,
                          val obb: File?,
                          val deobfuscation: File?)
