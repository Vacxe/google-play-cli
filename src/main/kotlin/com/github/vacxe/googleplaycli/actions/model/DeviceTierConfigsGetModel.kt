package com.github.vacxe.googleplaycli.actions.model

class DeviceTierConfigsGetModel(val packageName: String,
                                val deviceTierConfigId: Long,
                                requestParameters: String?
): RequestModel(requestParameters)