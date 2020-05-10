package com.github.vacxe.googleplaycli.actions

import com.github.vacxe.googleplaycli.actions.model.InternalappsharingartifactsUploadapkModel
import com.github.vacxe.googleplaycli.actions.model.InternalappsharingartifactsUploadbundleModel
import com.github.vacxe.googleplaycli.core.constants.MediaType
import com.google.api.client.http.AbstractInputStreamContent
import com.google.api.client.http.FileContent
import com.google.api.services.androidpublisher.model.InternalAppSharingArtifact

interface Internalappsharingartifacts : BaseAction {
    fun internalappsharingartifactsUploadapk(model: InternalappsharingartifactsUploadapkModel): InternalAppSharingArtifact {
        val apk: AbstractInputStreamContent = FileContent(MediaType.MIME_TYPE_APK, model.apk)
        return androidPublisher.Internalappsharingartifacts().uploadapk(model.packageName, apk).execute()
    }

    fun internalappsharingartifactsUploadbundle(model: InternalappsharingartifactsUploadbundleModel): InternalAppSharingArtifact {
        val bundle: AbstractInputStreamContent = FileContent(MediaType.MIME_TYPE_APK, model.bundle)
        return androidPublisher.Internalappsharingartifacts().uploadbundle(model.packageName, bundle).execute()
    }
}