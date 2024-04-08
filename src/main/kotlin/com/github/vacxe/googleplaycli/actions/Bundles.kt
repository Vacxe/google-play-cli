package com.github.vacxe.googleplaycli.actions

import com.github.vacxe.googleplaycli.actions.model.BundlesUploadModel
import com.github.vacxe.googleplaycli.actions.model.EditModel
import com.github.vacxe.googleplaycli.core.constants.MediaType.MIME_TYPE_STREAM
import com.google.api.client.http.AbstractInputStreamContent
import com.google.api.client.http.FileContent
import com.google.api.services.androidpublisher.AndroidPublisher
import com.google.api.services.androidpublisher.model.Bundle
import com.google.api.services.androidpublisher.model.BundlesListResponse

interface Bundles : BaseAction {
    fun bundlesList(model: EditModel): BundlesListResponse {
        val edits: AndroidPublisher.Edits = androidPublisher.edits()
        val editId = model.editId ?: edits.insert(model.packageName, null).execute().id
        return edits
            .bundles()
            .list(model.packageName, editId)
            .apply {
                model.parameters.forEach { (key, value) -> set(key, value) }
            }
            .execute()
    }

    fun bundlesUpload(model: BundlesUploadModel): Bundle {
        val edits: AndroidPublisher.Edits = androidPublisher.edits()
        val editId = model.editId ?: edits.insert(model.packageName, null).execute().id
        val bundleFile: AbstractInputStreamContent = FileContent(MIME_TYPE_STREAM, model.bundle)
        return edits.bundles().upload(model.packageName, editId, bundleFile)
            .apply {
                ackBundleInstallationWarning = model.ackBundleInstallationWarning
                model.deviceTierConfigId?.let { deviceTierConfigId -> setDeviceTierConfigId(deviceTierConfigId) }
            }
            .execute()
    }
}
