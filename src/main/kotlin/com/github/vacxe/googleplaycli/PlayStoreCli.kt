package com.github.vacxe.googleplaycli

import com.github.vacxe.googleplaycli.actions.apks.Apks
import com.github.vacxe.googleplaycli.actions.bundles.Bundles
import com.github.vacxe.googleplaycli.actions.deobfuscationfiles.Deobfuscationfiles
import com.github.vacxe.googleplaycli.actions.details.Details
import com.github.vacxe.googleplaycli.actions.expansionfiles.ExpansionFiles
import com.github.vacxe.googleplaycli.actions.images.Images
import com.github.vacxe.googleplaycli.actions.internalappsharingartifacts.Internalappsharingartifacts
import com.github.vacxe.googleplaycli.actions.listings.Listings
import com.github.vacxe.googleplaycli.actions.orders.Orders
import com.github.vacxe.googleplaycli.actions.reviews.Reviews
import com.github.vacxe.googleplaycli.actions.testers.Testers
import com.github.vacxe.googleplaycli.actions.tracks.Tracks
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.services.androidpublisher.AndroidPublisher
import com.google.api.services.androidpublisher.AndroidPublisherScopes
import com.google.auth.http.HttpCredentialsAdapter
import com.google.auth.oauth2.ServiceAccountCredentials
import java.io.File
import java.io.FileInputStream

class PlayStoreCli :
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
