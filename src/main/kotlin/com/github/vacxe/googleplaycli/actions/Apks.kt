package com.github.vacxe.googleplaycli.actions

import com.github.vacxe.googleplaycli.actions.model.ApksUploadModel
import com.github.vacxe.googleplaycli.actions.model.EditModel
import com.github.vacxe.googleplaycli.core.constants.MediaType.MIME_TYPE_APK
import com.google.api.client.http.AbstractInputStreamContent
import com.google.api.client.http.FileContent
import com.google.api.services.androidpublisher.AndroidPublisher
import com.google.api.services.androidpublisher.model.Apk
import com.google.api.services.androidpublisher.model.ApksListResponse

interface Apks : BaseAction {
    fun apksList(model: EditModel): ApksListResponse {
        val edits: AndroidPublisher.Edits = androidPublisher.edits()
        val editId = model.editId ?: edits.insert(model.packageName, null).execute().id
        return edits.apks().list(model.packageName, editId).execute()
    }

    fun apksUpload(model: ApksUploadModel): Apk {
        val edits: AndroidPublisher.Edits = androidPublisher.edits()
        val editId = model.editId ?: edits.insert(model.packageName, null).execute().id
        val apkFile: AbstractInputStreamContent = FileContent(MIME_TYPE_APK, model.apk)
        return edits.apks().upload(model.packageName, editId, apkFile).execute()
    }
}
