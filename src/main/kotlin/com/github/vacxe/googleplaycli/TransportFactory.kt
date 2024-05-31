package com.github.vacxe.googleplaycli

import com.github.vacxe.googleplaycli.environments.Proxy
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.http.HttpTransport
import com.google.api.client.http.apache.v2.ApacheHttpTransport
import com.google.api.client.http.javanet.NetHttpTransport
import org.apache.http.auth.AuthScope
import org.apache.http.auth.UsernamePasswordCredentials
import org.apache.http.impl.client.BasicCredentialsProvider
import org.apache.http.impl.client.ProxyAuthenticationStrategy
import java.io.FileInputStream
import java.security.KeyStore

object TransportFactory {
    private val host = Proxy.Environment.host
    private val port = Proxy.Environment.port
    private val username = Proxy.Environment.username
    private val password = Proxy.Environment.password
    private val trustStore = Proxy.Environment.trustStore
    private val trustStorePassword = Proxy.Environment.trustStorePassword

    fun buildTransport(): HttpTransport = when {
        host != null && port != null -> createHttpTransportProxy(
            host,
            port,
            username,
            password
        )

        trustStore != null && trustStorePassword != null -> createHttpTransportTrustStore(
            trustStore,
            trustStorePassword
        )

        else -> GoogleNetHttpTransport.newTrustedTransport()
    }

    private fun createHttpTransportProxy(
        host: String,
        port: String,
        username: String?,
        password: String?
    ): HttpTransport {
        val httpClient = ApacheHttpTransport.newDefaultHttpClientBuilder()
            .setProxyAuthenticationStrategy(ProxyAuthenticationStrategy.INSTANCE)
            .apply {
                if (username != null && password != null) {
                    val credentials = BasicCredentialsProvider().apply { }
                    credentials.setCredentials(
                        AuthScope(host, port.toInt()),
                        UsernamePasswordCredentials(username, password)
                    )
                    setDefaultCredentialsProvider(credentials)
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
