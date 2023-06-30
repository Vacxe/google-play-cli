package com.github.vacxe.googleplaycli.actions.model

class InappproductsDeleteModel(
        val packageName: String,
        val sku: String,
        requestParameters: String?
): RequestModel(requestParameters)