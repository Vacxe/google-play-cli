package com.github.vacxe.apkpublisher.actions.expansionfiles

import com.github.vacxe.apkpublisher.actions.BaseAction
import com.github.vacxe.apkpublisher.core.constants.MediaType
import com.google.api.client.http.AbstractInputStreamContent
import com.google.api.client.http.FileContent
import com.google.api.services.androidpublisher.AndroidPublisher
import com.google.api.services.androidpublisher.model.ExpansionFilesUploadResponse
import java.io.File

interface ExpansionFileAction : BaseAction {
    fun expansionFileUpload(expansionFile: File): ExpansionFilesUploadResponse? {
        checkNotNull(appVersionId, { println("App version isn't specified") })

        val edits: AndroidPublisher.Edits = androidPublisher.edits()
        val insert = edits.insert(appName, null).execute()
        val expansionFileContent: AbstractInputStreamContent = FileContent(MediaType.MIME_TYPE_APK, expansionFile)

        val result = edits.Expansionfiles()
            .upload(
                appName,
                insert.id,
                appVersionId?.toInt(),
                "main",
                expansionFileContent
            ).execute()
        println("Success: expansion file uploaded")

        return result
    }
}
