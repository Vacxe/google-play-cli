package com.github.vacxe.googleplaycli.actions.deobfuscationfiles

import com.github.vacxe.googleplaycli.actions.BaseAction
import com.github.vacxe.googleplaycli.actions.deobfuscationfiles.models.DeobfuscationfilesUploadModel
import com.github.vacxe.googleplaycli.core.constants.MediaType.MIME_TYPE_APK
import com.google.api.client.http.AbstractInputStreamContent
import com.google.api.client.http.FileContent
import com.google.api.services.androidpublisher.AndroidPublisher
import com.google.api.services.androidpublisher.model.DeobfuscationFilesUploadResponse

interface Deobfuscationfiles : BaseAction {
    fun deobfuscationFilesUpload(model: DeobfuscationfilesUploadModel): DeobfuscationFilesUploadResponse {
        val edits: AndroidPublisher.Edits = androidPublisher.edits()
        val insert = edits.insert(model.packageName, null).execute()
        val deobfuscation: AbstractInputStreamContent = FileContent(MIME_TYPE_APK, model.deobfuscation)
        return edits.Deobfuscationfiles().upload(model.packageName, insert.id, model.apkVersionCode, model.deobfuscationFileType, deobfuscation).execute()
    }
}
