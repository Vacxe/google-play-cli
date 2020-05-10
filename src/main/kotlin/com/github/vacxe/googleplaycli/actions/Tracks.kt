package com.github.vacxe.googleplaycli.actions

import com.github.vacxe.googleplaycli.actions.model.DefaultModel
import com.github.vacxe.googleplaycli.actions.model.TracksGetModel
import com.github.vacxe.googleplaycli.actions.model.TracksPatchModel
import com.github.vacxe.googleplaycli.actions.model.TracksUpdateModel
import com.google.api.services.androidpublisher.AndroidPublisher
import com.google.api.services.androidpublisher.model.Track
import com.google.api.services.androidpublisher.model.TrackRelease
import com.google.api.services.androidpublisher.model.TracksListResponse

interface Tracks : BaseAction {
    fun tracksList(model: DefaultModel): TracksListResponse {
        val edits: AndroidPublisher.Edits = androidPublisher.edits()
        val insert = edits.insert(model.packageName, null).execute()
        return edits.Tracks().list(model.packageName, insert.id).execute()
    }

    fun tracksGet(model: TracksGetModel): Track {
        val edits: AndroidPublisher.Edits = androidPublisher.edits()
        val insert = edits.insert(model.packageName, null).execute()
        return edits.Tracks().get(model.packageName, insert.id, model.track).execute()
    }

    fun tracksPatch(model: TracksPatchModel): Track {
        val edits: AndroidPublisher.Edits = androidPublisher.edits()
        val insert = edits.insert(model.packageName, null).execute()

        val track = Track().setReleases(
                listOf(TrackRelease().apply {
                    versionCodes = mutableListOf(model.apkVersionCode.toLong())
                    userFraction = model.userFraction
                })
        )
        return edits.Tracks().patch(model.packageName, insert.id, model.track, track).execute()
    }

    fun tracksUpdate(model: TracksUpdateModel): Track {
        val edits: AndroidPublisher.Edits = androidPublisher.edits()
        val insert = edits.insert(model.packageName, null).execute()

        val track = Track().setReleases(
                listOf(TrackRelease().apply {
                    versionCodes = mutableListOf(model.apkVersionCode.toLong())
                })
        )
        return edits.Tracks().update(model.packageName, insert.id, model.track, track).execute()
    }
}