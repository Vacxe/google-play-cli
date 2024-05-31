package com.github.vacxe.googleplaycli

import com.github.vacxe.googleplaycli.environments.Env
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.http.HttpTransport
import com.google.api.client.http.apache.v2.ApacheHttpTransport
import com.google.api.client.http.javanet.NetHttpTransport
import org.apache.http.HttpHost
import org.apache.http.auth.AuthScope
import org.apache.http.auth.UsernamePasswordCredentials
import org.apache.http.client.CredentialsProvider
import org.apache.http.client.HttpClient
import org.apache.http.conn.routing.HttpRoutePlanner
import org.apache.http.impl.client.BasicCredentialsProvider
import org.apache.http.impl.client.ProxyAuthenticationStrategy
import org.apache.http.impl.conn.DefaultProxyRoutePlanner
import java.io.FileInputStream
import java.security.KeyStore


object TransportFactory {
    private val host = Env.Proxy.host
    private val port = Env.Proxy.port
    private val username = Env.Proxy.username
    private val password = Env.Proxy.password
    private val trustStore = Env.TrustStore.trustStore
    private val trustStorePassword = Env.TrustStore.trustStorePassword

    fun buildTransport(): HttpTransport = when {
        host != null && port != null -> createHttpTransportProxy(
            host,
            port.toInt(),
            username,
            password
        )

        trustStore != null && trustStorePassword != null -> createHttpTransportTrustStore(
            trustStore,
            trustStorePassword
        )

        else -> GoogleNetHttpTransport.newTrustedTransport()
    }

    fun createHttpTransportProxy(
        proxyHost: String,
        proxyPort: Int,
        proxyUsername: String?,
        proxyPassword: String?
    ): HttpTransport {
        val proxyHostDetails = HttpHost(proxyHost, proxyPort)
        val httpRoutePlanner: HttpRoutePlanner = DefaultProxyRoutePlanner(proxyHostDetails)
        val httpClient: HttpClient = ApacheHttpTransport.newDefaultHttpClientBuilder()
            .setRoutePlanner(httpRoutePlanner)
            .setProxyAuthenticationStrategy(ProxyAuthenticationStrategy.INSTANCE)
            .apply {
                if (proxyUsername != null && proxyPassword != null) {
                    val credentialsProvider: CredentialsProvider = BasicCredentialsProvider()
                    credentialsProvider.setCredentials(
                        AuthScope(proxyHostDetails.hostName, proxyHostDetails.port),
                        UsernamePasswordCredentials(proxyUsername, proxyPassword)
                    )
                    setDefaultCredentialsProvider(credentialsProvider)
                }
            }
            .build()
        return ApacheHttpTransport(httpClient)
    }

    private fun createHttpTransportTrustStore(
        trustStore: String,
        trustStorePassword: String
    ): HttpTransport {
        val ks = KeyStore.getInstance(KeyStore.getDefaultType())
        FileInputStream(trustStore).use { fis ->
            ks.load(fis, trustStorePassword.toCharArray())
        }
        return NetHttpTransport.Builder().trustCertificates(ks).build()
    }
}
