package com.github.vacxe.googleplaycli.actions.tracks.mapper

import com.github.vacxe.googleplaycli.actions.tracks.configuration.TracksListConfiguration
import com.github.vacxe.googleplaycli.actions.tracks.model.TracksListModel

class TracksListMapper {
    fun map(configuration: TracksListConfiguration): TracksListModel = TracksListModel(
            packageName = configuration.packageName
    )
}