package com.github.vacxe.apkpublisher.actions

import com.google.api.services.androidpublisher.AndroidPublisher

interface BaseAction {
    val androidPublisher: AndroidPublisher
    val appName: String
    val appVersionId: Long?
}
