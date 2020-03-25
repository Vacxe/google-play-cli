package com.github.vacxe.apkpublisher.actions.apks

import com.github.vacxe.apkpublisher.actions.BaseAction
import com.github.vacxe.apkpublisher.core.constants.MediaType.MIME_TYPE_APK
import com.google.api.client.http.AbstractInputStreamContent
import com.google.api.client.http.FileContent
import com.google.api.services.androidpublisher.AndroidPublisher
import com.google.api.services.androidpublisher.model.Apk
import com.google.api.services.androidpublisher.model.ApksListResponse
import java.io.File

interface ApksAction : BaseAction {
    fun apksList(): ApksListResponse {
        val edits: AndroidPublisher.Edits = androidPublisher.edits()
        val insert = edits.insert(appName, null).execute()
        return edits.Apks().list(appName, insert.id).execute()
    }

    fun apksUpload(apk: File): Apk {
        val edits: AndroidPublisher.Edits = androidPublisher.edits()
        val insert = edits.insert(appName, null).execute()
        val apkFile: AbstractInputStreamContent = FileContent(MIME_TYPE_APK, apk)

        val result = edits.Apks().upload(appName, insert.id, apkFile).execute()
        println("Success: apk with versionCode: ${result.versionCode} uploaded")

        return result
    }
}
