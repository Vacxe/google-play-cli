package com.github.vacxe.googleplaycli.actions.tracks.mappers

import com.github.vacxe.googleplaycli.actions.tracks.configuration.TracksListConfiguration
import com.github.vacxe.googleplaycli.actions.tracks.models.TracksListModel

class TracksListMapper {
    fun map(configuration: TracksListConfiguration): TracksListModel = TracksListModel(
            packageName = configuration.packageName
    )
}