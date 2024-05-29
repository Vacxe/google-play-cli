package com.github.vacxe.googleplaycli.environments

import java.time.Duration

object Env {
    val serviceAccoutJsonFile: String
        get() = System.getenv("PLAYSTORE_SERVICE_ACCOUNT_JSON_FILE") ?: ""

    val serviceAccoutJsonContent: String
        get() = System.getenv("PLAYSTORE_SERVICE_ACCOUNT_JSON_CONTENT") ?: ""

    val packageName: String
        get() = System.getenv("APP_PACKAGE_NAME") ?: ""

    val connectionTimeout: Duration
        get() =(System.getenv("PLAYSTORE_CONNECTION_TIMEOUT") ?: "PT2M")
            .let { Duration.parse(it) }
}
