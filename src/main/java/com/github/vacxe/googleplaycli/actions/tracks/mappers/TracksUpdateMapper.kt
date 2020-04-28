package com.github.vacxe.googleplaycli.actions.tracks.mappers

import com.github.vacxe.googleplaycli.actions.tracks.configuration.TracksUpdateConfiguration
import com.github.vacxe.googleplaycli.actions.tracks.models.TracksUpdateModel

class TracksUpdateMapper {
    fun map(configuration: TracksUpdateConfiguration): TracksUpdateModel = TracksUpdateModel(
            packageName = configuration.packageName,
            track = configuration.track,
            apkVersionCode = configuration.apkVersionCode
    )
}