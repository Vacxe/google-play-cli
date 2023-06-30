package com.github.vacxe.googleplaycli.actions.model

class TracksPatchModel(val packageName: String,
                            val editId: String?,
                            val track: String,
                            val apkVersionCode: Int,
                            val userFraction: Double,
                            requestParameters: String?
): RequestModel(requestParameters)