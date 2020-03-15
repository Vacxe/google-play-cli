package com.github.vacxe.apkpublisher.actions.apks

import com.google.api.services.androidpublisher.AndroidPublisher
import com.google.api.services.androidpublisher.model.ApksListResponse

interface Apks {
    abstract val androidPublisher: AndroidPublisher
    abstract val appName: String

    fun apksList(): ApksListResponse {
        val edits: AndroidPublisher.Edits = androidPublisher.edits()
        val insert = edits.insert(appName, null).execute()
        return edits.Apks().list(appName, insert.id).execute()
    }
}