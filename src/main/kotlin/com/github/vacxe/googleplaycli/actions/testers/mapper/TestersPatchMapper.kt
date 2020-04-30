package com.github.vacxe.googleplaycli.actions.testers.mapper

import com.github.vacxe.googleplaycli.actions.testers.configuration.TestersPatchConfiguration
import com.github.vacxe.googleplaycli.actions.testers.model.TestersPatchModel
import com.github.vacxe.googleplaycli.core.nullIfEmpty

class TestersPatchMapper {
    fun map(configuration: TestersPatchConfiguration): TestersPatchModel = TestersPatchModel(
            packageName = configuration.packageName,
            track = configuration.track,
            googleGroups = configuration.googleGroups.nullIfEmpty()?.split(","),
            googlePlusCommunities = configuration.googlePlusCommunities.nullIfEmpty()?.split(",")
    )
}