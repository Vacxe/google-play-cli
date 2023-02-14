package com.github.vacxe.googleplaycli

import com.github.vacxe.googleplaycli.actions.*
import com.google.api.client.googleapis.GoogleUtils
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.services.androidpublisher.AndroidPublisher
import com.google.api.services.androidpublisher.AndroidPublisherScopes
import com.google.auth.http.HttpCredentialsAdapter
import com.google.auth.oauth2.ServiceAccountCredentials
import java.io.ByteArrayInputStream
import java.io.File
import java.io.FileInputStream
import java.io.InputStream
import java.net.InetSocketAddress
import java.net.Proxy

class PlayStoreApi(serviceAccountInputStream: InputStream, appName: String) :
        Edit,
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
        Reviews,
        Inappproducts {

    override val androidPublisher: AndroidPublisher

    init {
        val accountCredentials = ServiceAccountCredentials
                .fromStream(serviceAccountInputStream)
                .createScoped(listOf(AndroidPublisherScopes.ANDROIDPUBLISHER))

        val proxy : String? = System.getenv("PLAY_STORE_PROXY")
        val httpTransport = when {
            proxy != null ->  {
                val proxyParameters = proxy.split(":")
                NetHttpTransport.Builder()
                    .trustCertificates(GoogleUtils.getCertificateTrustStore())
                    .setProxy(Proxy(Proxy.Type.HTTP, InetSocketAddress(proxyParameters[0], proxyParameters[1].toInt())))
                    .build()
            }
            else -> GoogleNetHttpTransport.newTrustedTransport()
        }

        androidPublisher = AndroidPublisher.Builder(
                httpTransport,
                JacksonFactory.getDefaultInstance(),
                HttpCredentialsAdapter(accountCredentials)
        )
                .setApplicationName(appName)
                .build()
    }
}
