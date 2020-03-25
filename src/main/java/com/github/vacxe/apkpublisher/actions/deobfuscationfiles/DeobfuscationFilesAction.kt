package com.github.vacxe.apkpublisher.actions.deobfuscationfiles

import com.github.vacxe.apkpublisher.actions.BaseAction
import com.github.vacxe.apkpublisher.core.constants.MediaType
import com.google.api.client.http.AbstractInputStreamContent
import com.google.api.client.http.FileContent
import com.google.api.services.androidpublisher.AndroidPublisher
import com.google.api.services.androidpublisher.model.DeobfuscationFilesUploadResponse
import java.io.File

interface DeobfuscationFilesAction : BaseAction {
    fun deobfuscationFilesUpload(deobfuscationFile: File): DeobfuscationFilesUploadResponse {
        checkNotNull(appVersionId, { println("App version isn't specified") })

        val edits: AndroidPublisher.Edits = androidPublisher.edits()
        val insert = edits.insert(appName, null).execute()
        val deobfuscationFileContent: AbstractInputStreamContent =
            FileContent(MediaType.MIME_TYPE_APK, deobfuscationFile)

        val result = edits.Deobfuscationfiles().upload(
            appName,
            insert.id,
            appVersionId?.toInt(),
            "proguard",
            deobfuscationFileContent
        ).execute()
        println("Success: deobfuscation file uploaded")

        return result
    }
}
