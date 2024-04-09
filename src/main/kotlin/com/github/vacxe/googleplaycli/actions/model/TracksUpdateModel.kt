package com.github.vacxe.googleplaycli.actions.model

class TracksUpdateModel(
    val packageName: String,
    val editId: String?,
    val track: String,
    val apkVersionCode: Int,
    val userFraction: Double,
    val status: String,
    requestParameters: String?
) : RequestModel(requestParameters)