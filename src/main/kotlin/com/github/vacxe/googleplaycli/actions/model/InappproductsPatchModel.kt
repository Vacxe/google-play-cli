package com.github.vacxe.googleplaycli.actions.model

import java.nio.file.Path

class InappproductsPatchModel(
        val packageName: String,
        val sku: String,
        val jsonPath: Path,
        val autoConvertMissingPrices: Boolean,
        requestParameters: String?
): RequestModel(requestParameters)