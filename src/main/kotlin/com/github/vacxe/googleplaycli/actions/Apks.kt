package com.github.vacxe.googleplaycli.actions

import com.github.vacxe.googleplaycli.actions.model.ApksUploadModel
import com.github.vacxe.googleplaycli.actions.model.DefaultModel
import com.github.vacxe.googleplaycli.core.constants.MediaType.MIME_TYPE_APK
import com.google.api.client.http.AbstractInputStreamContent
import com.google.api.client.http.FileContent
import com.google.api.services.androidpublisher.AndroidPublisher
import com.google.api.services.androidpublisher.model.Apk
import com.google.api.services.androidpublisher.model.ApksListResponse

interface Apks : BaseAction {
    fun apksList(model: DefaultModel): ApksListResponse {
        val edits: AndroidPublisher.Edits = androidPublisher.edits()
        val insert = edits.insert(model.packageName, null).execute()
        return edits.Apks().list(model.packageName, insert.id).execute()
    }

    fun apksUpload(model: ApksUploadModel): Apk {
        val edits: AndroidPublisher.Edits = androidPublisher.edits()
        val insert = edits.insert(model.packageName, null).execute()
        val apkFile: AbstractInputStreamContent = FileContent(MIME_TYPE_APK, model.apk)
        return edits.Apks().upload(model.packageName, insert.id, apkFile).execute()
    }
}
