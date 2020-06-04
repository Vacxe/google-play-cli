package com.github.vacxe.googleplaycli.actions

import com.github.vacxe.googleplaycli.actions.model.ExpansionFilesGetModel
import com.github.vacxe.googleplaycli.actions.model.ExpansionFilesPatchModel
import com.github.vacxe.googleplaycli.actions.model.ExpansionFilesUpdateModel
import com.github.vacxe.googleplaycli.actions.model.ExpansionFilesUploadModel
import com.github.vacxe.googleplaycli.core.constants.MediaType
import com.google.api.client.http.AbstractInputStreamContent
import com.google.api.client.http.FileContent
import com.google.api.services.androidpublisher.AndroidPublisher
import com.google.api.services.androidpublisher.model.ExpansionFile
import com.google.api.services.androidpublisher.model.ExpansionFilesUploadResponse

interface ExpansionFiles : BaseAction {
    fun expansionFilesGet(model: ExpansionFilesGetModel): ExpansionFile {
        val edits: AndroidPublisher.Edits = androidPublisher.edits()
        val editId = model.editId ?: edits.insert(model.packageName, null).execute().id
        return edits.expansionfiles().get(model.packageName, editId, model.apkVersionCode, model.expansionFileType).execute()
    }

    fun expansionFilesPatch(model: ExpansionFilesPatchModel): ExpansionFile {
        val edits: AndroidPublisher.Edits = androidPublisher.edits()
        val editId = model.editId ?: edits.insert(model.packageName, null).execute().id
        val expansionFile = ExpansionFile().apply {
            model.fileSize?.let { fileSize = it }
            model.referencesVersion?.let { referencesVersion = it }
        }

        return edits.expansionfiles()
                .patch(
                        model.packageName,
                        editId,
                        model.apkVersionCode,
                        model.expansionFileType,
                        expansionFile)
                .execute()
    }

    fun expansionFilesUpdate(model: ExpansionFilesUpdateModel): ExpansionFile {
        val edits: AndroidPublisher.Edits = androidPublisher.edits()
        val editId = model.editId ?: edits.insert(model.packageName, null).execute().id
        val expansionFile = ExpansionFile().apply {
            fileSize = model.fileSize
            referencesVersion = model.referencesVersion
        }

        return edits.expansionfiles()
                .update(
                        model.packageName,
                        editId,
                        model.apkVersionCode,
                        model.expansionFileType,
                        expansionFile)
                .execute()
    }

    fun expansionFilesUpload(model: ExpansionFilesUploadModel): ExpansionFilesUploadResponse {
        val edits: AndroidPublisher.Edits = androidPublisher.edits()
        val editId = model.editId ?: edits.insert(model.packageName, null).execute().id
        val expansionFile: AbstractInputStreamContent = FileContent(MediaType.MIME_TYPE_APK, model.expansionFile)

        return edits.expansionfiles()
                .upload(
                        model.packageName,
                        editId,
                        model.apkVersionCode,
                        model.expansionFileType,
                        expansionFile)
                .execute()
    }
}
