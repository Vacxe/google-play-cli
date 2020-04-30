package com.github.vacxe.googleplaycli.actions.listings

import com.github.vacxe.googleplaycli.actions.BaseAction
import com.github.vacxe.googleplaycli.actions.core.model.DefaultModel
import com.github.vacxe.googleplaycli.actions.listings.model.ListingsDeleteModel
import com.github.vacxe.googleplaycli.actions.listings.model.ListingsGetModel
import com.github.vacxe.googleplaycli.actions.listings.model.ListingsPatchModel
import com.github.vacxe.googleplaycli.actions.listings.model.ListingsUpdateModel
import com.google.api.services.androidpublisher.AndroidPublisher
import com.google.api.services.androidpublisher.model.Listing
import com.google.api.services.androidpublisher.model.ListingsListResponse

interface Listings : BaseAction {
    fun listingsList(model: DefaultModel): ListingsListResponse {
        val edits: AndroidPublisher.Edits = androidPublisher.edits()
        val insert = edits.insert(model.packageName, null).execute()
        return edits.Listings().list(model.packageName, insert.id).execute()
    }

    fun listingsDeleteAll(model: DefaultModel): Void {
        val edits: AndroidPublisher.Edits = androidPublisher.edits()
        val insert = edits.insert(model.packageName, null).execute()
        return edits.Listings().deleteall(model.packageName, insert.id).execute()
    }

    fun listingsDelete(model: ListingsDeleteModel): Void {
        val edits: AndroidPublisher.Edits = androidPublisher.edits()
        val insert = edits.insert(model.packageName, null).execute()
        return edits.Listings().delete(model.packageName, insert.id, model.language).execute()
    }

    fun listingsGet(model: ListingsGetModel): Listing {
        val edits: AndroidPublisher.Edits = androidPublisher.edits()
        val insert = edits.insert(model.packageName, null).execute()
        return edits.Listings().get(model.packageName, insert.id, model.language).execute()
    }

    fun listingsUpdate(model: ListingsUpdateModel): Listing {
        val edits: AndroidPublisher.Edits = androidPublisher.edits()
        val insert = edits.insert(model.packageName, null).execute()
        val listing = Listing().apply {
            fullDescription = model.fullDescription
            shortDescription = model.shortDescription
            title = model.title
            video = model.video
        }
        return edits.Listings().update(model.packageName, insert.id, model.language, listing).execute()
    }

    fun listingsPatch(model: ListingsPatchModel): Listing {
        val edits: AndroidPublisher.Edits = androidPublisher.edits()
        val insert = edits.insert(model.packageName, null).execute()
        val listing = Listing().apply {
            model.fullDescription?.let { fullDescription = it }
            model.shortDescription?.let { shortDescription = it }
            model.title?.let { title = it }
            model.video?.let { video = it }
        }
        return edits.Listings().update(model.packageName, insert.id, model.language, listing).execute()
    }
}