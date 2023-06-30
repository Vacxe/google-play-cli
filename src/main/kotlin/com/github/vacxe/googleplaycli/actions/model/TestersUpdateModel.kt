package com.github.vacxe.googleplaycli.actions.model

class TestersUpdateModel(val packageName: String,
                         val editId: String?,
                         val track: String,
                         val googleGroups: List<String>,
                         requestParameters: String?
): RequestModel(requestParameters)
