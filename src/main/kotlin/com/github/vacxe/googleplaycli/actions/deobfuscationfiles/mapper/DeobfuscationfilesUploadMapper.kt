package com.github.vacxe.googleplaycli.actions.deobfuscationfiles.mapper

import com.github.vacxe.googleplaycli.actions.deobfuscationfiles.configuration.DeobfuscationfilesUploadConfiguration
import com.github.vacxe.googleplaycli.actions.deobfuscationfiles.model.DeobfuscationfilesUploadModel
import java.io.File

class DeobfuscationfilesUploadMapper {
    fun map(configuration: DeobfuscationfilesUploadConfiguration): DeobfuscationfilesUploadModel = DeobfuscationfilesUploadModel(
            packageName = configuration.packageName,
            apkVersionCode = configuration.apkVersionCode,
            deobfuscation = File(configuration.deobfuscation),
            deobfuscationFileType = configuration.deobfuscationFileType
    )
}