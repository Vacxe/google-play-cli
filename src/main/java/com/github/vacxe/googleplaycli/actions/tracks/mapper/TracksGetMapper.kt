package com.github.vacxe.googleplaycli.actions.tracks.mapper

import com.github.vacxe.googleplaycli.actions.tracks.configuration.TracksGetConfiguration
import com.github.vacxe.googleplaycli.actions.tracks.model.TracksGetModel

class TracksGetMapper {
    fun map(configuration: TracksGetConfiguration): TracksGetModel = TracksGetModel(
            packageName = configuration.packageName,
            track = configuration.track
    )
}