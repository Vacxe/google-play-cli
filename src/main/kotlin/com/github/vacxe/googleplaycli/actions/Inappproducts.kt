package com.github.vacxe.googleplaycli.actions

import com.github.vacxe.googleplaycli.actions.model.DefaultModel
import com.github.vacxe.googleplaycli.actions.model.InappproductsDeleteModel
import com.github.vacxe.googleplaycli.actions.model.InappproductsGetModel
import com.github.vacxe.googleplaycli.actions.model.InappproductsInsertModel
import com.github.vacxe.googleplaycli.actions.model.InappproductsPatchModel
import com.github.vacxe.googleplaycli.actions.model.InappproductsUpdateModel
import com.google.api.services.androidpublisher.model.InAppProduct
import com.google.api.services.androidpublisher.model.InappproductsListResponse
import java.nio.file.Files

interface Inappproducts : BaseAction {

    fun inappproductsDelete(model: InappproductsDeleteModel): Void {
        return androidPublisher.inappproducts().delete(model.packageName, model.sku).execute()
    }

    fun inappproductsGet(model: InappproductsGetModel): InAppProduct {
        return androidPublisher.inappproducts().get(model.packageName, model.sku).execute()
    }

    fun inappproductsInsert(model: InappproductsInsertModel): InAppProduct {
        val product = Files.newInputStream(model.jsonPath).use {
            androidPublisher.jsonFactory.fromInputStream(it, InAppProduct::class.java)
        }

        return androidPublisher
                .inappproducts()
                .insert(model.packageName, product)
                .setAutoConvertMissingPrices(model.autoConvertMissingPrices)
                .execute()
    }

    fun inappproductsList(model: DefaultModel): InappproductsListResponse {
        return androidPublisher.inappproducts().list(model.packageName).execute()
    }

    fun inappproductsPatch(model: InappproductsPatchModel): InAppProduct {
        val product = Files.newInputStream(model.jsonPath).use {
            androidPublisher.jsonFactory.fromInputStream(it, InAppProduct::class.java)
        }

        return androidPublisher
                .inappproducts()
                .patch(model.packageName, model.sku, product)
                .setAutoConvertMissingPrices(model.autoConvertMissingPrices)
                .execute()
    }

    fun inappproductsUpdate(model: InappproductsUpdateModel): InAppProduct {
        val product = Files.newInputStream(model.jsonPath).use {
            androidPublisher.jsonFactory.fromInputStream(it, InAppProduct::class.java)
        }

        return androidPublisher
                .inappproducts()
                .update(model.packageName, model.sku, product)
                .setAutoConvertMissingPrices(model.autoConvertMissingPrices)
                .execute()
    }

}