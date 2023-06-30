package com.github.vacxe.googleplaycli.actions.model

class DetailsPatchModel(
    val packageName: String,
    val editId: String?,
    val contactEmail: String?,
    val contactPhone: String?,
    val contactWebsite: String?,
    val defaultLanguage: String?,
    parameters: String?
) : RequestModel(parameters)