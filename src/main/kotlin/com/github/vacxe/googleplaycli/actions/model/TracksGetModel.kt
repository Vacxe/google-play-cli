package com.github.vacxe.googleplaycli.actions.model

class TracksGetModel(val packageName: String,
                          val editId: String?,
                          val track: String,
                          requestParameters: String?
): RequestModel(requestParameters)