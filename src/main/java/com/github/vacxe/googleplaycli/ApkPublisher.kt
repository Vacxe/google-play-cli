@file:Suppress("IMPLICIT_CAST_TO_ANY")

package com.github.vacxe.googleplaycli

import com.xenomachina.argparser.mainBody
import java.util.*

fun main(args: Array<String>): Unit = mainBody {
    val command = ArrayDeque<String>().apply { addAll(args) }
    val result = when (command.pollFirst()) {
        "apks" -> {
            when (command.pollFirst()) {
                "list" -> Commands.Apks.list(command.toTypedArray())
                "upload" -> Commands.Apks.upload(command.toTypedArray())
                else -> "Command not found. Available: list, upload"
            }
        }
        "bundles" -> {
            when (command.pollFirst()) {
                "list" -> Commands.Bundles.list(command.toTypedArray())
                "upload" -> Commands.Bundles.upload(command.toTypedArray())
                else -> "Command not found. Available: list, upload"
            }
        }
        "deobfuscationfiles" -> {
            when (command.pollFirst()) {
                "upload" -> Commands.Deobfuscationfiles.upload(command.toTypedArray())
                else -> "Command not found. Available: upload"
            }
        }
        "details" -> {
            when (command.pollFirst()) {
                "get" -> Commands.Details.get(command.toTypedArray())
                "patch" -> Commands.Details.patch(command.toTypedArray())
                "update" -> Commands.Details.update(command.toTypedArray())
                else -> "Command not found. Available: get, patch, update"
            }
        }
        "listings" -> {
            when (command.pollFirst()) {
                "deleteall" -> Commands.Listings.deleteAll(command.toTypedArray())
                "list" -> Commands.Listings.list(command.toTypedArray())
                "get" -> Commands.Listings.get(command.toTypedArray())
                "delete" -> Commands.Listings.delete(command.toTypedArray())
                "update" -> Commands.Listings.update(command.toTypedArray())
                "patch" -> Commands.Listings.patch(command.toTypedArray())
                else -> "Command not found. Available: delete, deleteall, get, list, patch, update"
            }
        }
        "testers" -> {
            when (command.pollFirst()) {
                "get" -> Commands.Testers.get(command.toTypedArray())
                "patch" -> Commands.Testers.patch(command.toTypedArray())
                "update" -> Commands.Testers.update(command.toTypedArray())
                else -> "Command not found. Available: get, patch, update"
            }
        }
        "tracks" -> {
            when (command.pollFirst()) {
                "get" -> Commands.Tracks.get(command.toTypedArray())
                "list" -> Commands.Tracks.list(command.toTypedArray())
                "patch" -> Commands.Tracks.patch(command.toTypedArray())
                "update" -> Commands.Tracks.update(command.toTypedArray())
                else -> "Command not found. Available: get, list, patch, update"
            }
        }
        "reviews" -> {
            when (command.pollFirst()) {
                "list" -> Commands.Reviews.list(command.toTypedArray())
                "get" -> Commands.Reviews.get(command.toTypedArray())
                "reply" -> Commands.Reviews.reply(command.toTypedArray())
                else -> "Command not found. Available: get, list, reply"
            }

        }
        "internalappsharingartifacts" -> {
            when (command.pollFirst()) {
                "uploadapk" -> Commands.Internalappsharingartifacts.uploadApk(command.toTypedArray())
                "uploadbundle" -> Commands.Internalappsharingartifacts.uploadBundle(command.toTypedArray())
                else -> "Command not found. Available: uploadapk, uploadbundle"
            }
        }
        "orders" -> {
            when (command.pollFirst()) {
                "refund" -> Commands.Orders.refund(command.toTypedArray())
                else -> "Command not found. Available: refund"
            }
        }
        else -> "Command not found. Available: apks, bundles, deobfuscationfiles, tracks"
    }

    println(result)
}
