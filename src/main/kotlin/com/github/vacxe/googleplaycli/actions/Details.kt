package com.github.vacxe.googleplaycli.actions

import com.github.vacxe.googleplaycli.actions.model.DefaultModel
import com.github.vacxe.googleplaycli.actions.model.DetailsPatchModel
import com.github.vacxe.googleplaycli.actions.model.DetailsUpdateModel
import com.google.api.services.androidpublisher.AndroidPublisher
import com.google.api.services.androidpublisher.model.AppDetails

interface Details : BaseAction {
    fun detailsGet(model: DefaultModel): AppDetails {
        val edits: AndroidPublisher.Edits = androidPublisher.edits()
        val insert = edits.insert(model.packageName, null).execute()
        return edits.details().get(model.packageName, insert.id).execute()
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
        return edits.details().patch(model.packageName, insert.id, appDetails).execute()
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
        return edits.details().update(model.packageName, insert.id, appDetails).execute()
    }

}
