package com.github.vacxe.googleplaycli.actions

import com.github.vacxe.googleplaycli.actions.model.DeobfuscationfilesUploadModel
import com.github.vacxe.googleplaycli.core.constants.MediaType.MIME_TYPE_STREAM
import com.google.api.client.http.AbstractInputStreamContent
import com.google.api.client.http.FileContent
import com.google.api.services.androidpublisher.AndroidPublisher
import com.google.api.services.androidpublisher.model.DeobfuscationFilesUploadResponse

interface DeobfuscationFiles : BaseAction {
    fun deobfuscationFilesUpload(model: DeobfuscationfilesUploadModel): DeobfuscationFilesUploadResponse {
        val edits: AndroidPublisher.Edits = androidPublisher.edits()
        val editId = model.editId ?: edits.insert(model.packageName, null).execute().id
        val deobfuscation: AbstractInputStreamContent = FileContent(MIME_TYPE_STREAM, model.deobfuscation)
        return edits
            .deobfuscationfiles()
            .upload(model.packageName, editId, model.apkVersionCode, model.deobfuscationFileType, deobfuscation)
            .apply {
                model.parameters.forEach { (key, value) -> set(key, value) }
            }
            .execute()
    }
}
