package com.github.vacxe.googleplaycli.actions.tracks.mapper

import com.github.vacxe.googleplaycli.actions.tracks.configuration.TracksUpdateConfiguration
import com.github.vacxe.googleplaycli.actions.tracks.model.TracksUpdateModel

class TracksUpdateMapper {
    fun map(configuration: TracksUpdateConfiguration): TracksUpdateModel = TracksUpdateModel(
            packageName = configuration.packageName,
            track = configuration.track,
            apkVersionCode = configuration.apkVersionCode
    )
}