package com.github.vacxe.apkpublisher

import com.github.vacxe.apkpublisher.actions.apks.ApksAction
import com.github.vacxe.apkpublisher.actions.deobfuscationfiles.DeobfuscationFilesAction
import com.github.vacxe.apkpublisher.actions.expansionfiles.ExpansionFileAction
import com.github.vacxe.apkpublisher.actions.tracks.TracksAction
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.services.androidpublisher.AndroidPublisher
import com.google.api.services.androidpublisher.AndroidPublisherScopes
import com.google.auth.http.HttpCredentialsAdapter
import com.google.auth.oauth2.ServiceAccountCredentials
import java.io.File
import java.io.FileInputStream

class AndroidPublisherManager : ApksAction, DeobfuscationFilesAction, TracksAction, ExpansionFileAction {

    override val androidPublisher: AndroidPublisher
    override val appName: String
    override val appVersionId: Long?

    /**
     * Constructor based on provided APK file
     */
    constructor(
        serviceAccountJson: File,
        appName: String,
        appVersionId: Long
    ) {

        this.appName = appName
        this.appVersionId = appVersionId

        val accountCredentials = ServiceAccountCredentials
            .fromStream(FileInputStream(serviceAccountJson))
            .createScoped(listOf(AndroidPublisherScopes.ANDROIDPUBLISHER))

        androidPublisher = AndroidPublisher.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                JacksonFactory.getDefaultInstance(),
                HttpCredentialsAdapter(accountCredentials)
            )
            .setApplicationName(appName)
            .build()
    }
}
