package com.github.vacxe.apkpublisher

import com.github.vacxe.apkpublisher.args.UploadApkConfiguration
import com.github.vacxe.apkpublisher.core.apk.AppInfoExtractor
import com.xenomachina.argparser.ArgParser
import com.xenomachina.argparser.mainBody
import java.io.File

fun main(args: Array<String>): Unit = mainBody {
    ArgParser(args).parseInto(::UploadApkConfiguration).run {
        val appInfo = AppInfoExtractor().extractAppInfo(File(apk))
        val manager = AndroidPublisherManager(serviceAccountJson, appInfo.name)
        val apkList = manager.apksList()
        print(apkList)
}}
