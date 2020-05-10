package com.github.vacxe.googleplaycli

import com.github.vacxe.googleplaycli.actions.*
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.services.androidpublisher.AndroidPublisher
import com.google.api.services.androidpublisher.AndroidPublisherScopes
import com.google.auth.http.HttpCredentialsAdapter
import com.google.auth.oauth2.ServiceAccountCredentials
import java.io.File
import java.io.FileInputStream

class PlayStoreApi :
        Apks,
        Bundles,
        Deobfuscationfiles,
        Details,
        ExpansionFiles,
        Images,
        Listings,
        Testers,
        Tracks,
        Internalappsharingartifacts,
        Orders,
        Reviews {

    override val androidPublisher: AndroidPublisher

    constructor(
            serviceAccountJson: File,
            appName: String
    ) {
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
