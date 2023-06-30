package com.github.vacxe.googleplaycli.actions.model

import java.nio.file.Path

class InappproductsInsertModel(
        val packageName: String,
        val jsonPath: Path,
        val autoConvertMissingPrices: Boolean,
        requestParameters: String?
): RequestModel(requestParameters)