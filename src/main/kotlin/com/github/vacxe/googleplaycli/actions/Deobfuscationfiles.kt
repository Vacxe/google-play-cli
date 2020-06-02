package com.github.vacxe.googleplaycli.actions

import com.github.vacxe.googleplaycli.actions.model.DeobfuscationfilesUploadModel
import com.github.vacxe.googleplaycli.core.constants.MediaType.MIME_TYPE_APK
import com.google.api.client.http.AbstractInputStreamContent
import com.google.api.client.http.FileContent
import com.google.api.services.androidpublisher.AndroidPublisher
import com.google.api.services.androidpublisher.model.DeobfuscationFilesUploadResponse

interface Deobfuscationfiles : BaseAction {
    fun deobfuscationFilesUpload(model: DeobfuscationfilesUploadModel): DeobfuscationFilesUploadResponse {
        val edits: AndroidPublisher.Edits = androidPublisher.edits()
        val editId = model.editId ?: edits.insert(model.packageName, null).execute().id
        val deobfuscation: AbstractInputStreamContent = FileContent(MIME_TYPE_APK, model.deobfuscation)
        return edits.deobfuscationfiles().upload(model.packageName, editId, model.apkVersionCode, model.deobfuscationFileType, deobfuscation).execute()
    }
}
