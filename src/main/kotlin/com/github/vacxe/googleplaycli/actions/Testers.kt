package com.github.vacxe.googleplaycli.actions

import com.github.vacxe.googleplaycli.actions.model.TestersGetModel
import com.github.vacxe.googleplaycli.actions.model.TestersPatchModel
import com.github.vacxe.googleplaycli.actions.model.TestersUpdateModel
import com.google.api.services.androidpublisher.AndroidPublisher
import com.google.api.services.androidpublisher.model.Testers

interface Testers : BaseAction {

    fun testersGet(model: TestersGetModel): Testers {
        val edits: AndroidPublisher.Edits = androidPublisher.edits()
        val insert = edits.insert(model.packageName, null).execute()
        return edits.testers().get(model.packageName, insert.id, model.track).execute()
    }

    fun testersUpdate(model: TestersUpdateModel): Testers {
        val edits: AndroidPublisher.Edits = androidPublisher.edits()
        val insert = edits.insert(model.packageName, null).execute()
        val testers = Testers().apply {
            googleGroups = model.googleGroups
            googlePlusCommunities = model.googlePlusCommunities
        }
        return edits.testers().update(model.packageName, insert.id, model.track, testers).execute()
    }

    fun testersPatch(model: TestersPatchModel): Testers {
        val edits: AndroidPublisher.Edits = androidPublisher.edits()
        val insert = edits.insert(model.packageName, null).execute()
        val testers = Testers().apply {
            model.googleGroups?.let { googleGroups = it }
            model.googlePlusCommunities?.let { googlePlusCommunities = it }
        }
        return edits.testers().update(model.packageName, insert.id, model.track, testers).execute()
    }
}
