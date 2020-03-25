package com.github.vacxe.apkpublisher.models

import com.github.vacxe.apkpublisher.args.UploadApkConfiguration
import java.io.File

class UploadApkModelMapper {
    fun map(configuration: UploadApkConfiguration): UploadApkModel = UploadApkModel(
        apk = File(configuration.apk),
        track = configuration.track,
        expansion = if (configuration.expansion.isNotEmpty()) File(configuration.expansion) else null,
        deobfuscation = if (configuration.deobfuscation.isNotEmpty()) File(configuration.deobfuscation) else null
    )
}
