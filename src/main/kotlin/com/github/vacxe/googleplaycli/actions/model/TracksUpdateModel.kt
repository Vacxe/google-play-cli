package com.github.vacxe.googleplaycli.actions.model

data class TracksUpdateModel(val packageName: String,
                             val editId: String?,
                             val track: String,
                             val apkVersionCode: Int,
                             val userFraction: Double)