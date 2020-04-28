package com.github.vacxe.googleplaycli.actions.tracks.mappers

import com.github.vacxe.googleplaycli.actions.tracks.configuration.TracksPatchConfiguration
import com.github.vacxe.googleplaycli.actions.tracks.models.TracksPatchModel

class TracksPatchMapper {
    fun map(configuration: TracksPatchConfiguration): TracksPatchModel = TracksPatchModel(
            packageName = configuration.packageName,
            track = configuration.track,
            apkVersionCode = configuration.apkVersionCode
    )
}