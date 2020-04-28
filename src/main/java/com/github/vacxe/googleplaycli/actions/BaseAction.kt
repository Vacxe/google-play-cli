package com.github.vacxe.googleplaycli.actions

import com.google.api.services.androidpublisher.AndroidPublisher

interface BaseAction {
    val androidPublisher: AndroidPublisher
}
