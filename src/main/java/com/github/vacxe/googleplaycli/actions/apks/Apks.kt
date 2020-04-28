package com.github.vacxe.googleplaycli.actions.apks

import com.github.vacxe.googleplaycli.actions.BaseAction
import com.github.vacxe.googleplaycli.actions.apks.configuration.ApksListConfiguration
import com.github.vacxe.googleplaycli.actions.apks.configuration.ApksUploadConfiguration
import com.github.vacxe.googleplaycli.actions.apks.models.ApksListModel
import com.github.vacxe.googleplaycli.actions.apks.models.ApksUploadModel
import com.github.vacxe.googleplaycli.actions.bundles.models.BundlesListModel
import com.github.vacxe.googleplaycli.actions.bundles.models.BundlesUploadModel
import com.github.vacxe.googleplaycli.core.constants.MediaType.MIME_TYPE_APK
import com.google.api.client.http.AbstractInputStreamContent
import com.google.api.client.http.FileContent
import com.google.api.services.androidpublisher.AndroidPublisher
import com.google.api.services.androidpublisher.model.Apk
import com.google.api.services.androidpublisher.model.ApksListResponse

interface Apks : BaseAction {
    fun apksList(model: ApksListModel): ApksListResponse {
        val edits: AndroidPublisher.Edits = androidPublisher.edits()
        val insert = edits.insert(model.packageName, null).execute()
        return edits.Apks().list(model.packageName, insert.id).execute()
    }

    fun apksUpload(model: ApksUploadModel): Apk {
        val edits: AndroidPublisher.Edits = androidPublisher.edits()
        val insert = edits.insert(model.packageName, null).execute()
        val apkFile: AbstractInputStreamContent = FileContent(MIME_TYPE_APK, model.apk)
        return edits.Apks().upload(model.packageName, insert.id, apkFile).execute()
    }
}
