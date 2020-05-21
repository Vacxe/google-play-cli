package com.github.vacxe.googleplaycli.actions

import com.github.vacxe.googleplaycli.actions.model.ImagesDeleteAllModel
import com.github.vacxe.googleplaycli.actions.model.ImagesDeleteModel
import com.github.vacxe.googleplaycli.actions.model.ImagesListModel
import com.github.vacxe.googleplaycli.actions.model.ImagesUploadModel
import com.github.vacxe.googleplaycli.core.constants.MediaType
import com.google.api.client.http.AbstractInputStreamContent
import com.google.api.client.http.FileContent
import com.google.api.services.androidpublisher.AndroidPublisher
import com.google.api.services.androidpublisher.model.ImagesDeleteAllResponse
import com.google.api.services.androidpublisher.model.ImagesListResponse
import com.google.api.services.androidpublisher.model.ImagesUploadResponse

interface Images : BaseAction {
    fun imagesList(model: ImagesListModel): ImagesListResponse {
        val edits: AndroidPublisher.Edits = androidPublisher.edits()
        val insert = edits.insert(model.packageName, null).execute()
        return edits.images().list(model.packageName, insert.id, model.language, model.imageType).execute()
    }

    fun imagesDelete(model: ImagesDeleteModel): Void {
        val edits: AndroidPublisher.Edits = androidPublisher.edits()
        val insert = edits.insert(model.packageName, null).execute()
        return edits.images().delete(model.packageName, insert.id, model.language, model.imageType, model.imageId).execute()
    }

    fun imagesDeleteAll(model: ImagesDeleteAllModel): ImagesDeleteAllResponse {
        val edits: AndroidPublisher.Edits = androidPublisher.edits()
        val insert = edits.insert(model.packageName, null).execute()
        return edits.images().deleteall(model.packageName, insert.id, model.language, model.imageType).execute()
    }

    fun imagesUploadAll(model: ImagesUploadModel): ImagesUploadResponse {
        val edits: AndroidPublisher.Edits = androidPublisher.edits()
        val insert = edits.insert(model.packageName, null).execute()
        val image: AbstractInputStreamContent = FileContent(MediaType.MIME_TYPE_PNG, model.image)
        return edits.images().upload(model.packageName, insert.id, model.language, model.imageType, image).execute()
    }
}
