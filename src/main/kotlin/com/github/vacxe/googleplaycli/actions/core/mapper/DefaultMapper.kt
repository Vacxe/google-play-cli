package com.github.vacxe.googleplaycli.actions.core.mapper

import com.github.vacxe.googleplaycli.actions.core.configuration.DefaultConfiguration
import com.github.vacxe.googleplaycli.actions.core.model.DefaultModel

class DefaultMapper {
    fun map(configuration: DefaultConfiguration): DefaultModel = DefaultModel(
            packageName = configuration.packageName
    )
}