package com.github.vacxe.googleplaycli.actions

import com.github.vacxe.googleplaycli.actions.model.*
import com.google.api.services.androidpublisher.AndroidPublisher
import com.google.api.services.androidpublisher.model.DeviceTierConfig
import com.google.api.services.androidpublisher.model.ListDeviceTierConfigsResponse

interface DeviceTierConfigs : BaseAction {
    fun deviceTierConfigsList(model: DeviceTierConfigsListModel): ListDeviceTierConfigsResponse? {
        val applications: AndroidPublisher.Applications = androidPublisher.applications()
        return applications
            .deviceTierConfigs()
            .list(model.packageName)
            .apply {
                model.parameters.forEach { (key, value) -> set(key, value) }
            }
            .execute()
    }

    fun deviceTierConfigsGet(model: DeviceTierConfigsGetModel): DeviceTierConfig {
        val applications: AndroidPublisher.Applications = androidPublisher.applications()
        return applications
            .deviceTierConfigs()
            .get(model.packageName, model.deviceTierConfigId)
            .apply {
                model.parameters.forEach { (key, value) -> set(key, value) }
            }
            .execute()
    }
}
