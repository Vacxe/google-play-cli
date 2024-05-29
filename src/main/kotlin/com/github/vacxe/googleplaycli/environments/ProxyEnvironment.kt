package com.github.vacxe.googleplaycli.environments

object ProxyEnvironment  {
    fun apply() {
        System.getenv("PLAYSTORE_PROXY")?.run {
            val proxyParameters = split(":")
            val host = proxyParameters[0]
            val port = proxyParameters[1]

            System.setProperty("proxySet", "true");
            System.setProperty("proxyHost", host)
            System.setProperty("proxyPort", port)
        }

        System.getenv("PLAYSTORE_PROXY_USER")?.run {
            System.setProperty("proxyUser", this)
        }

        System.getenv("PLAYSTORE_PROXY_PASSWORD")?.run {
            System.setProperty("proxyPassword", this)
        }
    }
}
