package com.github.vacxe.googleplaycli.core

import com.github.ajalt.clikt.parameters.options.option

abstract class EditCommand(name: String, actionDescription: String = "") : BaseCommand(name, actionDescription) {
    val editId: String? by option("--edit-id", "-id", help = "Edit id")
}