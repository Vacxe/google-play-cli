package com.github.vacxe.apkpublisher

import com.github.vacxe.apkpublisher.models.AppInfo
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.services.androidpublisher.AndroidPublisher
import com.google.api.services.androidpublisher.AndroidPublisherScopes
import com.google.api.services.androidpublisher.model.ApksListResponse
import com.google.auth.http.HttpCredentialsAdapter
import com.google.auth.oauth2.ServiceAccountCredentials
import java.io.File
import java.io.FileInputStream
import java.io.IOException

class PlayServiceManager {
    @Throws(IOException::class)
    fun authWithServiceAccount(serviceAccountJson: File, appInfo: AppInfo){
        val accountCredentials = ServiceAccountCredentials
            .fromStream( FileInputStream(serviceAccountJson))
            .createScoped(listOf(AndroidPublisherScopes.ANDROIDPUBLISHER))

        val pub = AndroidPublisher.Builder(
            GoogleNetHttpTransport.newTrustedTransport(),
            JacksonFactory.getDefaultInstance(),
            HttpCredentialsAdapter(accountCredentials)
        ).setApplicationName(appInfo.name).build()

        val edits: AndroidPublisher.Edits = pub.edits()
        val e = edits.insert(appInfo.name, null).execute()
        val get: AndroidPublisher.Edits.Apks.List = edits.Apks().list(appInfo.name, e.id)

        val appDetails: ApksListResponse = get.execute()
        println(appDetails)
    }
}
