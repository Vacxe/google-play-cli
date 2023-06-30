package com.github.vacxe.googleplaycli.actions.model

class InappproductsGetModel(
        val packageName: String,
        val sku: String,
        requestParameters: String?
): RequestModel(requestParameters)