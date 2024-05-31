package com.github.vacxe.googleplaycli.environments

import java.time.Duration

object Env {
    object PlayConsole {
        val serviceAccoutJsonFile: String
            get() = System.getenv("PLAYSTORE_SERVICE_ACCOUNT_JSON_FILE") ?: ""

        val serviceAccoutJsonContent: String
            get() = System.getenv("PLAYSTORE_SERVICE_ACCOUNT_JSON_CONTENT") ?: ""

        val packageName: String
            get() = System.getenv("APP_PACKAGE_NAME") ?: ""
    }

    object Network {
        val connectionTimeout: Duration
            get() = (System.getenv("PLAYSTORE_CONNECTION_TIMEOUT") ?: "PT2M")
                .let { Duration.parse(it) }
    }

    object Proxy {
        val host: String?
            get() = System.getenv("PLAYSTORE_PROXY_HOST")

        val port: String?
            get() = System.getenv("PLAYSTORE_PROXY_PORT")

        val username: String?
            get() = System.getenv("PLAYSTORE_PROXY_USERNAME")

        val password: String?
            get() = System.getenv("PLAYSTORE_PROXY_PASSWORD")
    }

    object TrustStore {
        val trustStore: String?
            get() = System.getenv("PLAYSTORE_PROXY_TRUST_STORE")

        val trustStorePassword: String?
            get() = System.getenv("PLAYSTORE_PROXY_TRUST_STORE_PASSWORD")
    }
}
