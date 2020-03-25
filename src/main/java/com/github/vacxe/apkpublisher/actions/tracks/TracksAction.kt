package com.github.vacxe.apkpublisher.actions.tracks

import com.github.vacxe.apkpublisher.actions.BaseAction
import com.google.api.services.androidpublisher.AndroidPublisher
import com.google.api.services.androidpublisher.model.Track
import com.google.api.services.androidpublisher.model.TrackRelease

interface TracksAction : BaseAction {
    fun updateTrack(trackName: String): Track {
        checkNotNull(appVersionId, { println("App version isn't specified") })

        val edits: AndroidPublisher.Edits = androidPublisher.edits()
        val insert = edits.insert(appName, null).execute()

        val track = Track().setReleases(
            listOf(TrackRelease().apply {
                versionCodes = mutableListOf(appVersionId)
            })
        )

        val result = edits.Tracks().update(appName, insert.id, trackName, track).execute()
        println("Success: Track \"${result.track}\" has been set for releases: ${result.releases}")

        return result
    }
}
