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
        val insert = edits.insert(model.packageName, null).execute()
        return edits.expansionfiles().get(model.packageName, insert.id, model.apkVersionCode, model.expansionFileType).execute()
    }

    fun expansionFilesPatch(model: ExpansionFilesPatchModel): ExpansionFile {
        val edits: AndroidPublisher.Edits = androidPublisher.edits()
        val insert = edits.insert(model.packageName, null).execute()
        val expansionFile = ExpansionFile().apply {
            model.fileSize?.let { fileSize = it }
            model.referencesVersion?.let { referencesVersion = it }
        }

        return edits.expansionfiles()
                .patch(
                        model.packageName,
                        insert.id,
                        model.apkVersionCode,
                        model.expansionFileType,
                        expansionFile)
                .execute()
    }

    fun expansionFilesUpdate(model: ExpansionFilesUpdateModel): ExpansionFile {
        val edits: AndroidPublisher.Edits = androidPublisher.edits()
        val insert = edits.insert(model.packageName, null).execute()
        val expansionFile = ExpansionFile().apply {
            fileSize = model.fileSize
            referencesVersion = model.referencesVersion
        }

        return edits.expansionfiles()
                .update(
                        model.packageName,
                        insert.id,
                        model.apkVersionCode,
                        model.expansionFileType,
                        expansionFile)
                .execute()
    }

    fun expansionFilesUpload(model: ExpansionFilesUploadModel): ExpansionFilesUploadResponse {
        val edits: AndroidPublisher.Edits = androidPublisher.edits()
        val insert = edits.insert(model.packageName, null).execute()
        val expansionFile: AbstractInputStreamContent = FileContent(MediaType.MIME_TYPE_APK, model.expansionFile)

        return edits.expansionfiles()
                .upload(
                        model.packageName,
                        insert.id,
                        model.apkVersionCode,
                        model.expansionFileType,
                        expansionFile)
                .execute()
    }
}
