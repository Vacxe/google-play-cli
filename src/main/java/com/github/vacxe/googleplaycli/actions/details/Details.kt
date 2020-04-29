package com.github.vacxe.googleplaycli.actions.details

import com.github.vacxe.googleplaycli.actions.BaseAction
import com.github.vacxe.googleplaycli.actions.core.model.DefaultModel
import com.github.vacxe.googleplaycli.actions.details.model.DetailsPatchModel
import com.github.vacxe.googleplaycli.actions.details.model.DetailsUpdateModel
import com.google.api.services.androidpublisher.AndroidPublisher
import com.google.api.services.androidpublisher.model.AppDetails

interface Details : BaseAction {
    fun detailsGet(model: DefaultModel): AppDetails {
        val edits: AndroidPublisher.Edits = androidPublisher.edits()
        val insert = edits.insert(model.packageName, null).execute()
        return edits.Details().get(model.packageName, insert.id).execute()
    }

    fun detailsPatch(model: DetailsPatchModel): AppDetails {
        val edits: AndroidPublisher.Edits = androidPublisher.edits()
        val insert = edits.insert(model.packageName, null).execute()
        val appDetails = AppDetails().apply {
            model.contactEmail?.let { contactEmail = it }
            model.contactPhone?.let { contactPhone = it }
            model.contactWebsite?.let { contactWebsite = it }
            model.defaultLanguage?.let { defaultLanguage = it }
        }
        return edits.Details().patch(model.packageName, insert.id, appDetails).execute()
    }

    fun detailsUpdate(model: DetailsUpdateModel): AppDetails {
        val edits: AndroidPublisher.Edits = androidPublisher.edits()
        val insert = edits.insert(model.packageName, null).execute()
        val appDetails = AppDetails().apply {
            model.contactEmail?.let { contactEmail = it }
            model.contactPhone?.let { contactPhone = it }
            model.contactWebsite?.let { contactWebsite = it }
            model.defaultLanguage?.let { defaultLanguage = it }
        }
        return edits.Details().update(model.packageName, insert.id, appDetails).execute()
    }

}
