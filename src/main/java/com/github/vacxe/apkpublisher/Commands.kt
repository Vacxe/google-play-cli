package com.github.vacxe.apkpublisher

import com.github.vacxe.apkpublisher.args.UploadApkConfiguration
import com.github.vacxe.apkpublisher.core.apk.AppInfoExtractor
import com.github.vacxe.apkpublisher.models.UploadApkModelMapper
import com.xenomachina.argparser.ArgParser
import java.io.File

object Commands {
    fun uploadApk(args: Array<String>) {
        ArgParser(args).parseInto(::UploadApkConfiguration).run {
            val appInfo = AppInfoExtractor().extractAppInfo(File(apk))
            val manager = AndroidPublisherManager(serviceAccountJson, appInfo.name, appInfo.versionId)
            val uploadApkModel = UploadApkModelMapper().map(this)

            manager.run {
                apksUpload(uploadApkModel.apk)
                updateTrack(uploadApkModel.track)
                uploadApkModel.expansion?.let { expansionFileUpload(it) }
                uploadApkModel.deobfuscation?.let { deobfuscationFilesUpload(it) }
            }
        }
    }
}
