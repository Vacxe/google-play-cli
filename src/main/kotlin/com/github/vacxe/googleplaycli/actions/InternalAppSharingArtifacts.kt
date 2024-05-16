package com.github.vacxe.googleplaycli.actions

import com.github.vacxe.googleplaycli.actions.model.InternalAppSharingArtifactsUploadApkModel
import com.github.vacxe.googleplaycli.actions.model.InternalAppSharingArtifactsUploadBundleModel
import com.github.vacxe.googleplaycli.core.constants.MediaType
import com.google.api.client.http.AbstractInputStreamContent
import com.google.api.client.http.FileContent
import com.google.api.services.androidpublisher.model.InternalAppSharingArtifact

interface InternalAppSharingArtifacts : BaseAction {
    fun internalAppSharingArtifactsUploadApk(model: InternalAppSharingArtifactsUploadApkModel): InternalAppSharingArtifact {
        val apk: AbstractInputStreamContent = FileContent(MediaType.MIME_TYPE_APK, model.apk)
        return androidPublisher
            .internalappsharingartifacts()
            .uploadapk(model.packageName, apk)

            .apply {
                model.parameters.forEach { (key, value) -> set(key, value) }
            }
            .execute()
    }

    fun internalAppSharingArtifactsUploadBundle(model: InternalAppSharingArtifactsUploadBundleModel): InternalAppSharingArtifact {
        val bundle: AbstractInputStreamContent = FileContent(MediaType.MIME_TYPE_STREAM, model.bundle)
        return androidPublisher
            .internalappsharingartifacts()
            .uploadbundle(model.packageName, bundle)
            .apply {
                model.parameters.forEach { (key, value) -> set(key, value) }
            }
            .execute()
    }
}
