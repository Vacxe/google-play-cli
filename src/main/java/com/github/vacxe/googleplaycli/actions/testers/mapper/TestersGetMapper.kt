package com.github.vacxe.googleplaycli.actions.testers.mapper

import com.github.vacxe.googleplaycli.actions.testers.configuration.TestersGetConfiguration
import com.github.vacxe.googleplaycli.actions.testers.model.TestersGetModel

class TestersGetMapper {
    fun map(configuration: TestersGetConfiguration): TestersGetModel = TestersGetModel(
            packageName = configuration.packageName,
            track = configuration.track
    )
}