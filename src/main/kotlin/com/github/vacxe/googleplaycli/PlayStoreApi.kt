package com.github.vacxe.googleplaycli

import com.github.vacxe.googleplaycli.actions.*
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.http.HttpRequestInitializer
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.services.androidpublisher.AndroidPublisher
import com.google.api.services.androidpublisher.AndroidPublisherScopes
import com.google.auth.http.HttpCredentialsAdapter
import com.google.auth.oauth2.ServiceAccountCredentials
import java.io.InputStream
import java.time.Duration

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

        System.getenv("PLAYSTORE_PROXY")?.run {
            val proxyParameters = split(":")
            System.setProperty("proxyHost", proxyParameters[0])
            System.setProperty("proxyPort", proxyParameters[1])
        }

        val connectionTimeout = (System.getenv("PLAYSTORE_CONNECTION_TIMEOUT") ?: "PT2M")
            .let { Duration.parse(it) }

        androidPublisher = AndroidPublisher.Builder(
            GoogleNetHttpTransport.newTrustedTransport(),
            JacksonFactory.getDefaultInstance(),
            setHttpTimeout(HttpCredentialsAdapter(accountCredentials), connectionTimeout)
        )
            .setApplicationName(appName)
            .build()
    }

    private fun setHttpTimeout(requestInitializer: HttpRequestInitializer, timeout: Duration): HttpRequestInitializer {
        return HttpRequestInitializer { httpRequest ->
            requestInitializer.initialize(httpRequest)
            httpRequest.connectTimeout = timeout.toMillis().toInt()
            httpRequest.readTimeout = timeout.toMillis().toInt()
        }
    }
}
