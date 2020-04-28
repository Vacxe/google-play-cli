package com.github.vacxe.googleplaycli.actions.tracks.models

data class TracksPatchModel(val packageName: String,
                            val track: String,
                            val apkVersionCode: Int)