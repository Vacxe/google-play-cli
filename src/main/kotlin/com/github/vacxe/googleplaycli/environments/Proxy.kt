package com.github.vacxe.googleplaycli.environments

object Proxy {
    object Environment {
        val host: String?
            get() = System.getenv("PLAYSTORE_PROXY_HOST")

        val port: String?
            get() = System.getenv("PLAYSTORE_PROXY_PORT")

        val username: String?
            get() = System.getenv("PLAYSTORE_PROXY_USERNAME")

        val password: String?
            get() = System.getenv("PLAYSTORE_PROXY_PASSWORD")

        val trustStore: String?
            get() = System.getenv("PLAYSTORE_PROXY_TRUST_STORE")

        val trustStorePassword: String?
            get() = System.getenv("PLAYSTORE_PROXY_TRUST_STORE_PASSWORD")
    }

    fun apply() {
        val host = Proxy.Environment.host
        val port = Proxy.Environment.port
        val username = Proxy.Environment.username
        val password = Proxy.Environment.password
        val trustStore = Proxy.Environment.trustStore
        val trustStorePassword = Proxy.Environment.trustStorePassword

        if (host != null && port != null) {
            val protocols = arrayOf("http", "https")
            protocols.forEach { protocol ->
                System.setProperty("$protocol.proxySet", "true");
                System.setProperty("$protocol.proxyHost", host)
                System.setProperty("$protocol.proxyPort", port)
                username?.run { System.setProperty("$protocol.proxyUser", this) }
                password?.run { System.setProperty("$protocol.proxyPassword", this) }
            }
        }

        if(trustStore != null && trustStorePassword != null) {
            System.setProperty("javax.net.ssl.trustStore", trustStore)
            System.setProperty("javax.net.ssl.trustStorePassword", trustStorePassword)
        }
    }
}
