package com.github.vacxe.googleplaycli.actions.tracks.mapper

import com.github.vacxe.googleplaycli.actions.tracks.configuration.TracksPatchConfiguration
import com.github.vacxe.googleplaycli.actions.tracks.model.TracksPatchModel

class TracksPatchMapper {
    fun map(configuration: TracksPatchConfiguration): TracksPatchModel = TracksPatchModel(
            packageName = configuration.packageName,
            track = configuration.track,
            apkVersionCode = configuration.apkVersionCode,
            userFraction = configuration.userFraction
    )
}