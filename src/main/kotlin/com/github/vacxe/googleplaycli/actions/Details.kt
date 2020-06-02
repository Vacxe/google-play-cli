package com.github.vacxe.googleplaycli.actions

import com.github.vacxe.googleplaycli.actions.model.DetailsPatchModel
import com.github.vacxe.googleplaycli.actions.model.DetailsUpdateModel
import com.github.vacxe.googleplaycli.actions.model.EditModel
import com.google.api.services.androidpublisher.AndroidPublisher
import com.google.api.services.androidpublisher.model.AppDetails

interface Details : BaseAction {
    fun detailsGet(model: EditModel): AppDetails {
        val edits: AndroidPublisher.Edits = androidPublisher.edits()
        val editId = model.editId ?: edits.insert(model.packageName, null).execute().id
        return edits.details().get(model.packageName, editId).execute()
    }

    fun detailsPatch(model: DetailsPatchModel): AppDetails {
        val edits: AndroidPublisher.Edits = androidPublisher.edits()
        val editId = model.editId ?: edits.insert(model.packageName, null).execute().id
        val appDetails = AppDetails().apply {
            model.contactEmail?.let { contactEmail = it }
            model.contactPhone?.let { contactPhone = it }
            model.contactWebsite?.let { contactWebsite = it }
            model.defaultLanguage?.let { defaultLanguage = it }
        }
        return edits.details().patch(model.packageName, editId, appDetails).execute()
    }

    fun detailsUpdate(model: DetailsUpdateModel): AppDetails {
        val edits: AndroidPublisher.Edits = androidPublisher.edits()
        val editId = model.editId ?: edits.insert(model.packageName, null).execute().id
        val appDetails = AppDetails().apply {
            model.contactEmail?.let { contactEmail = it }
            model.contactPhone?.let { contactPhone = it }
            model.contactWebsite?.let { contactWebsite = it }
            model.defaultLanguage?.let { defaultLanguage = it }
        }
        return edits.details().update(model.packageName, editId, appDetails).execute()
    }

}
