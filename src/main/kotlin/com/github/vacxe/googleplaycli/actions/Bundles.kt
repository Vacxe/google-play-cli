package com.github.vacxe.googleplaycli.actions

import com.github.vacxe.googleplaycli.actions.model.BundlesUploadModel
import com.github.vacxe.googleplaycli.actions.model.EditModel
import com.github.vacxe.googleplaycli.core.constants.MediaType.MIME_TYPE_APK
import com.google.api.client.http.AbstractInputStreamContent
import com.google.api.client.http.FileContent
import com.google.api.services.androidpublisher.AndroidPublisher
import com.google.api.services.androidpublisher.model.Bundle
import com.google.api.services.androidpublisher.model.BundlesListResponse

interface Bundles : BaseAction {
    fun bundlesList(model: EditModel): BundlesListResponse {
        val edits: AndroidPublisher.Edits = androidPublisher.edits()
        val editId = model.editId ?: edits.insert(model.packageName, null).execute().id
        return edits.bundles().list(model.packageName, editId).execute()
    }

    fun bundlesUpload(model: BundlesUploadModel): Bundle {
        val edits: AndroidPublisher.Edits = androidPublisher.edits()
        val editId = model.editId ?: edits.insert(model.packageName, null).execute().id
        val apkFile: AbstractInputStreamContent = FileContent(MIME_TYPE_APK, model.bundle)
        return edits.bundles().upload(model.packageName, editId, apkFile)
                .apply { ackBundleInstallationWarning = model.ackBundleInstallationWarning }
                .execute()
    }
}
