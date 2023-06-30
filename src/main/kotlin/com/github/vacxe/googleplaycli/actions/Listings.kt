package com.github.vacxe.googleplaycli.actions

import com.github.vacxe.googleplaycli.actions.model.*
import com.google.api.services.androidpublisher.AndroidPublisher
import com.google.api.services.androidpublisher.model.Listing
import com.google.api.services.androidpublisher.model.ListingsListResponse

interface Listings : BaseAction {
    fun listingsList(model: EditModel): ListingsListResponse {
        val edits: AndroidPublisher.Edits = androidPublisher.edits()
        val editId = model.editId ?: edits.insert(model.packageName, null).execute().id
        return edits
            .listings()
            .list(model.packageName, editId)
            .apply {
                model.parameters.forEach { (key, value) -> set(key, value) }
            }
            .execute()
    }

    fun listingsDeleteAll(model: EditModel): Void {
        val edits: AndroidPublisher.Edits = androidPublisher.edits()
        val editId = model.editId ?: edits.insert(model.packageName, null).execute().id
        return edits
            .listings()
            .deleteall(model.packageName, editId)
            .apply {
                model.parameters.forEach { (key, value) -> set(key, value) }
            }
            .execute()
    }

    fun listingsDelete(model: ListingsDeleteModel): Void {
        val edits: AndroidPublisher.Edits = androidPublisher.edits()
        val editId = model.editId ?: edits.insert(model.packageName, null).execute().id
        return edits
            .listings()
            .delete(model.packageName, editId, model.language)
            .apply {
                model.parameters.forEach { (key, value) -> set(key, value) }
            }
            .execute()
    }

    fun listingsGet(model: ListingsGetModel): Listing {
        val edits: AndroidPublisher.Edits = androidPublisher.edits()
        val editId = model.editId ?: edits.insert(model.packageName, null).execute().id
        return edits
            .listings()
            .get(model.packageName, editId, model.language)
            .apply {
                model.parameters.forEach { (key, value) -> set(key, value) }
            }
            .execute()
    }

    fun listingsUpdate(model: ListingsUpdateModel): Listing {
        val edits: AndroidPublisher.Edits = androidPublisher.edits()
        val editId = model.editId ?: edits.insert(model.packageName, null).execute().id
        val listing = Listing().apply {
            fullDescription = model.fullDescription
            shortDescription = model.shortDescription
            title = model.title
            video = model.video
        }
        return edits
            .listings()
            .update(model.packageName, editId, model.language, listing)
            .apply {
                model.parameters.forEach { (key, value) -> set(key, value) }
            }
            .execute()
    }

    fun listingsPatch(model: ListingsPatchModel): Listing {
        val edits: AndroidPublisher.Edits = androidPublisher.edits()
        val editId = model.editId ?: edits.insert(model.packageName, null).execute().id
        val listing = Listing().apply {
            model.fullDescription?.let { fullDescription = it }
            model.shortDescription?.let { shortDescription = it }
            model.title?.let { title = it }
            model.video?.let { video = it }
        }
        return edits
            .listings()
            .update(model.packageName, editId, model.language, listing)
            .apply {
                model.parameters.forEach { (key, value) -> set(key, value) }
            }
            .execute()
    }
}
