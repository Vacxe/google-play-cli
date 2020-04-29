package com.github.vacxe.googleplaycli.actions.testers.mapper

import com.github.vacxe.googleplaycli.actions.testers.configuration.TestersUpdateConfiguration
import com.github.vacxe.googleplaycli.actions.testers.model.TestersUpdateModel

class TestersUpdateMapper {
    fun map(configuration: TestersUpdateConfiguration): TestersUpdateModel = TestersUpdateModel(
            packageName = configuration.packageName,
            track = configuration.track,
            googleGroups = configuration.googleGroups.split(","),
            googlePlusCommunities = configuration.googlePlusCommunities.split(",")
    )
}