package com.github.vacxe.googleplaycli.actions.expansionfiles

import com.github.vacxe.googleplaycli.actions.BaseAction
import com.github.vacxe.googleplaycli.actions.expansionfiles.model.ExpansionFilesGetModel
import com.github.vacxe.googleplaycli.actions.expansionfiles.model.ExpansionFilesPatchModel
import com.github.vacxe.googleplaycli.actions.expansionfiles.model.ExpansionFilesUpdateModel
import com.github.vacxe.googleplaycli.actions.expansionfiles.model.ExpansionFilesUploadModel
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
        return edits.Expansionfiles().get(model.packageName, insert.id, model.apkVersionCode, model.expansionFileType).execute()
    }

    fun expansionFilesPatch(model: ExpansionFilesPatchModel): ExpansionFile {
        val edits: AndroidPublisher.Edits = androidPublisher.edits()
        val insert = edits.insert(model.packageName, null).execute()
        val expansionFile = ExpansionFile().apply {
            model.fileSize?.let { fileSize = it }
            model.referencesVersion?.let { referencesVersion = it }
        }

        return edits.Expansionfiles()
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

        return edits.Expansionfiles()
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

        return edits.Expansionfiles()
                .upload(
                        model.packageName,
                        insert.id,
                        model.apkVersionCode,
                        model.expansionFileType,
                        expansionFile)
                .execute()
    }
}
