package com.github.vacxe.googleplaycli.environments

import java.net.Authenticator
import java.net.PasswordAuthentication

object SystemProxy {
    private val host = Env.Proxy.host
    private val port = Env.Proxy.port
    private val username = Env.Proxy.username
    private val password = Env.Proxy.password

    fun apply() {
        if(host != null && port != null) {
            System.setProperty("proxyEnabled", "true")
            System.setProperty("proxyHost", host)
            System.setProperty("proxyPort", port)

            if(username != null && password != null) {
                Authenticator.setDefault(
                    object : Authenticator() {
                        override fun getPasswordAuthentication(): PasswordAuthentication =
                            PasswordAuthentication(username, password.toCharArray())
                    }
                )

                System.setProperty("proxyUser", username)
                System.setProperty("proxyPassword", password)
                System.setProperty("jdk.http.auth.tunneling.disabledSchemes", "")
            }
        }
    }
}
