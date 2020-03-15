package com.github.vacxe.apkpublisher.core.apk

import com.github.vacxe.apkpublisher.models.AppInfo
import com.shazam.axmlparser.AXMLParser
import java.io.File
import java.io.IOException
import java.io.InputStream
import java.util.zip.ZipFile

class AppInfoExtractor {
    fun extractAppInfo(apk: File): AppInfo{
        var apkInputStream: InputStream? = null
        try {
            val zip = ZipFile(apk)
            val entry = zip.getEntry("AndroidManifest.xml")
            apkInputStream = zip.getInputStream(entry)

            val parser = AXMLParser(apkInputStream)
            var eventType = parser.type

            var appPackage: String? = null
            var appVersionId: Int = 0
            var appVersionName: String? = null
            while (eventType != AXMLParser.END_DOCUMENT) {
                if (eventType == AXMLParser.START_TAG) {
                    val parserName = parser.name
                    val isManifest = "manifest" == parserName
                    if (isManifest) {
                        for (i in 0 until parser.attributeCount) {
                            val parserAttributeName = parser.getAttributeName(i)
                            when(parserAttributeName){
                                "package" -> {
                                    appPackage = parser.getAttributeValueString(i)
                                }
                                "versionCode" -> {
                                    appVersionId = parser.getAttributeValue(i)
                                }
                                "versionName" -> {
                                    appVersionName = parser.getAttributeValueString(i)
                                }
                            }
                        }
                    }
                }
                eventType = parser.next()
            }
            if(appPackage != null && appVersionName != null) {
                return AppInfo(appPackage, appVersionId, appVersionName)
            }
            throw RuntimeException("Unable to extract appPackage or appVersionName from app AndroidManifest.xml.")

        } catch (e: IOException) {
            throw RuntimeException("Unable to parse AndroidManifest.xml.", e)
        } finally {
            apkInputStream?.close()
        }
    }
}