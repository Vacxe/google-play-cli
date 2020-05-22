package com.github.vacxe.googleplaycli.actions

import com.github.vacxe.googleplaycli.actions.model.BundlesUploadModel
import com.github.vacxe.googleplaycli.actions.model.DefaultModel
import com.github.vacxe.googleplaycli.core.constants.MediaType.MIME_TYPE_APK
import com.google.api.client.http.AbstractInputStreamContent
import com.google.api.client.http.FileContent
import com.google.api.services.androidpublisher.AndroidPublisher
import com.google.api.services.androidpublisher.model.Bundle
import com.google.api.services.androidpublisher.model.BundlesListResponse

interface Bundles : BaseAction {
    fun bundlesList(model: DefaultModel): BundlesListResponse {
        val edits: AndroidPublisher.Edits = androidPublisher.edits()
        val insert = edits.insert(model.packageName, null).execute()
        return edits.bundles().list(model.packageName, insert.id).execute()
    }

    fun bundlesUpload(model: BundlesUploadModel): Bundle {
        val edits: AndroidPublisher.Edits = androidPublisher.edits()
        val insert = edits.insert(model.packageName, null).execute()
        val apkFile: AbstractInputStreamContent = FileContent(MIME_TYPE_APK, model.bundle)
        return edits.bundles().upload(model.packageName, insert.id, apkFile)
                .apply { ackBundleInstallationWarning = model.ackBundleInstallationWarning }
                .execute()
    }
}
