package com.github.vacxe.googleplaycli

import com.github.vacxe.googleplaycli.actions.*
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.http.HttpRequestInitializer
import com.google.api.client.json.JsonFactory
import com.google.api.client.json.gson.GsonFactory
import com.google.api.services.androidpublisher.AndroidPublisher
import com.google.api.services.androidpublisher.AndroidPublisherScopes
import com.google.auth.http.HttpCredentialsAdapter
import com.google.auth.oauth2.ServiceAccountCredentials
import java.io.InputStream
import java.time.Duration

class PlayStoreApi(serviceAccountInputStream: InputStream, appName: String) :
    Apks,
    Bundles,
    Deobfuscationfiles,
    Details,
    DeviceTierConfigs,
    Edit,
    ExpansionFiles,
    Images,
    Inappproducts,
    Internalappsharingartifacts,
    Listings,
    Orders,
    Reviews,
    Testers,
    Tracks {

    override val androidPublisher: AndroidPublisher

    init {
        val accountCredentials = ServiceAccountCredentials
            .fromStream(serviceAccountInputStream)
            .createScoped(listOf(AndroidPublisherScopes.ANDROIDPUBLISHER))

        System.getenv("PLAYSTORE_PROXY")?.run {
            val proxyParameters = split(":")
            val host = proxyParameters[0]
            val port = proxyParameters[1]

            System.setProperty("proxySet", "true");
            System.setProperty("proxyHost", host)
            System.setProperty("proxyPort", port)
        }

        val connectionTimeout = (System.getenv("PLAYSTORE_CONNECTION_TIMEOUT") ?: "PT2M")
            .let { Duration.parse(it) }

        androidPublisher = AndroidPublisher.Builder(
            GoogleNetHttpTransport.newTrustedTransport(),
            GsonFactory.getDefaultInstance(),
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
