package com.github.vacxe.googleplaycli.actions

import com.github.vacxe.googleplaycli.actions.model.*
import com.google.api.services.androidpublisher.model.InAppProduct
import com.google.api.services.androidpublisher.model.InappproductsListResponse
import java.nio.file.Files

interface InAppProducts : BaseAction {

    fun inAppProductsDelete(model: InappproductsDeleteModel): Void {
        return androidPublisher
            .inappproducts()
            .delete(model.packageName, model.sku)
            .apply {
                model.parameters.forEach { (key, value) -> set(key, value) }
            }
            .execute()
    }

    fun inAppProductsGet(model: InappproductsGetModel): InAppProduct {
        return androidPublisher
            .inappproducts()
            .get(model.packageName, model.sku)
            .apply {
                model.parameters.forEach { (key, value) -> set(key, value) }
            }
            .execute()
    }

    fun inAppProductsInsert(model: InappproductsInsertModel): InAppProduct {
        val product = Files.newInputStream(model.jsonPath).use {
            androidPublisher.jsonFactory.fromInputStream(it, InAppProduct::class.java)
        }

        return androidPublisher
            .inappproducts()
            .insert(model.packageName, product)
            .setAutoConvertMissingPrices(model.autoConvertMissingPrices)
            .apply {
                model.parameters.forEach { (key, value) -> set(key, value) }
            }
            .execute()
    }

    fun inAppProductsList(model: DefaultModel): InappproductsListResponse {
        return androidPublisher.inappproducts()
            .list(model.packageName)
            .apply {
                model.parameters.forEach { (key, value) -> set(key, value) }
            }
            .execute()
    }

    fun inAppProductsPatch(model: InappproductsPatchModel): InAppProduct {
        val product = Files.newInputStream(model.jsonPath).use {
            androidPublisher.jsonFactory.fromInputStream(it, InAppProduct::class.java)
        }

        return androidPublisher
            .inappproducts()
            .patch(model.packageName, model.sku, product)
            .setAutoConvertMissingPrices(model.autoConvertMissingPrices)
            .apply {
                model.parameters.forEach { (key, value) -> set(key, value) }
            }
            .execute()
    }

    fun inAppProductsUpdate(model: InappproductsUpdateModel): InAppProduct {
        val product = Files.newInputStream(model.jsonPath).use {
            androidPublisher.jsonFactory.fromInputStream(it, InAppProduct::class.java)
        }

        return androidPublisher
            .inappproducts()
            .update(model.packageName, model.sku, product)
            .setAutoConvertMissingPrices(model.autoConvertMissingPrices)
            .apply {
                model.parameters.forEach { (key, value) -> set(key, value) }
            }
            .execute()
    }
}
