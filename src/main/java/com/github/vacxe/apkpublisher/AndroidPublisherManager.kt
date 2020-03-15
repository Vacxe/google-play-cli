package com.github.vacxe.apkpublisher

import com.github.vacxe.apkpublisher.actions.apks.Apks
import com.github.vacxe.apkpublisher.models.ApkInfo
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.services.androidpublisher.AndroidPublisher
import com.google.api.services.androidpublisher.AndroidPublisherScopes
import com.google.auth.http.HttpCredentialsAdapter
import com.google.auth.oauth2.ServiceAccountCredentials
import java.io.File
import java.io.FileInputStream

class AndroidPublisherManager : Apks{

    override val androidPublisher: AndroidPublisher
    override val appName: String

    constructor(serviceAccountJson: File, appName: String){
        this.appName = appName

        val accountCredentials = ServiceAccountCredentials
            .fromStream( FileInputStream(serviceAccountJson))
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
