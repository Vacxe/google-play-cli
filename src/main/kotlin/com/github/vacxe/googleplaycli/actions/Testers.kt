package com.github.vacxe.googleplaycli.actions

import com.github.vacxe.googleplaycli.actions.model.TestersGetModel
import com.github.vacxe.googleplaycli.actions.model.TestersPatchModel
import com.github.vacxe.googleplaycli.actions.model.TestersUpdateModel
import com.google.api.services.androidpublisher.AndroidPublisher
import com.google.api.services.androidpublisher.model.Testers

interface Testers : BaseAction {

    fun testersGet(model: TestersGetModel): Testers {
        val edits: AndroidPublisher.Edits = androidPublisher.edits()
        val editId = model.editId ?: edits.insert(model.packageName, null).execute().id
        return edits
            .testers()
            .get(model.packageName, editId, model.track)
            .apply {
                model.parameters.forEach { (key, value) -> set(key, value) }
            }
            .execute()
    }

    fun testersUpdate(model: TestersUpdateModel): Testers {
        val edits: AndroidPublisher.Edits = androidPublisher.edits()
        val editId = model.editId ?: edits.insert(model.packageName, null).execute().id
        val testers = Testers().apply {
            googleGroups = model.googleGroups
        }
        return edits
            .testers()
            .update(model.packageName, editId, model.track, testers)
            .apply {
                model.parameters.forEach { (key, value) -> set(key, value) }
            }
            .execute()
    }

    fun testersPatch(model: TestersPatchModel): Testers {
        val edits: AndroidPublisher.Edits = androidPublisher.edits()
        val editId = model.editId ?: edits.insert(model.packageName, null).execute().id
        val testers = Testers().apply {
            model.googleGroups?.let { googleGroups = it }
        }
        return edits
            .testers()
            .update(model.packageName, editId, model.track, testers)
            .apply {
                model.parameters.forEach { (key, value) -> set(key, value) }
            }
            .execute()
    }
}
