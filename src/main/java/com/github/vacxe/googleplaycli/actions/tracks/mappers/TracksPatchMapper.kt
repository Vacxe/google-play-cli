package com.github.vacxe.googleplaycli.actions.tracks.mappers

import com.github.vacxe.googleplaycli.actions.tracks.configuration.TracksListConfiguration
import com.github.vacxe.googleplaycli.actions.tracks.configuration.TracksPatchConfiguration
import com.github.vacxe.googleplaycli.actions.tracks.configuration.TracksUpdateConfiguration
import com.github.vacxe.googleplaycli.actions.tracks.models.TracksListModel
import com.github.vacxe.googleplaycli.actions.tracks.models.TracksPatchModel
import com.github.vacxe.googleplaycli.actions.tracks.models.TracksUpdateModel

class TracksPatchMapper {
    fun map(configuration: TracksPatchConfiguration): TracksPatchModel = TracksPatchModel(
            packageName = configuration.packageName,
            track = configuration.track,
            apkVersionCode = configuration.apkVersionCode
    )
}