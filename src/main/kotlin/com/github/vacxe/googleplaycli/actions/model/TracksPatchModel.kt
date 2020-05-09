package com.github.vacxe.googleplaycli.actions.model

data class TracksPatchModel(val packageName: String,
                            val track: String,
                            val apkVersionCode: Int,
                            val userFraction: Double)