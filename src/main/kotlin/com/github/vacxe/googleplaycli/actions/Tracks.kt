package com.github.vacxe.googleplaycli.actions

import com.github.vacxe.googleplaycli.actions.model.EditModel
import com.github.vacxe.googleplaycli.actions.model.TracksGetModel
import com.github.vacxe.googleplaycli.actions.model.TracksPatchModel
import com.github.vacxe.googleplaycli.actions.model.TracksUpdateModel
import com.google.api.services.androidpublisher.AndroidPublisher
import com.google.api.services.androidpublisher.model.Track
import com.google.api.services.androidpublisher.model.TrackRelease
import com.google.api.services.androidpublisher.model.TracksListResponse

interface Tracks : BaseAction {
    fun tracksList(model: EditModel): TracksListResponse {
        val edits: AndroidPublisher.Edits = androidPublisher.edits()
        val editId = model.editId ?: edits.insert(model.packageName, null).execute().id
        return edits
            .tracks()
            .list(model.packageName, editId)
            .apply {
                model.parameters.forEach { (key, value) -> set(key, value) }
            }
            .execute()
    }

    fun tracksGet(model: TracksGetModel): Track {
        val edits: AndroidPublisher.Edits = androidPublisher.edits()
        val editId = model.editId ?: edits.insert(model.packageName, null).execute().id
        return edits
            .tracks()
            .get(model.packageName, editId, model.track)
            .apply {
                model.parameters.forEach { (key, value) -> set(key, value) }
            }
            .execute()
    }

    fun tracksPatch(model: TracksPatchModel): Track {
        val edits: AndroidPublisher.Edits = androidPublisher.edits()
        val editId = model.editId ?: edits.insert(model.packageName, null).execute().id

        val track = Track().setReleases(
                listOf(TrackRelease().apply {
                    versionCodes = mutableListOf(model.apkVersionCode.toLong())
                    userFraction = model.userFraction
                })
        )
        return edits
            .tracks()
            .patch(model.packageName, editId, model.track, track)
            .apply {
                model.parameters.forEach { (key, value) -> set(key, value) }
            }
            .execute()
    }

    fun tracksUpdate(model: TracksUpdateModel): Track {
        val edits: AndroidPublisher.Edits = androidPublisher.edits()
        val editId = model.editId ?: edits.insert(model.packageName, null).execute().id

        val release = TrackRelease()
                .setVersionCodes(listOf(model.apkVersionCode.toLong()))
                .setStatus("completed")

        val track = Track().setReleases(listOf(release))

        return edits
            .tracks()
            .update(model.packageName, editId, model.track, track)
            .apply {
                model.parameters.forEach { (key, value) -> set(key, value) }
            }
            .execute()
    }
}
