@file:Suppress("IMPLICIT_CAST_TO_ANY")

package com.github.vacxe.googleplaycli

import com.github.vacxe.googleplaycli.actions.model.DefaultModel
import com.github.vacxe.googleplaycli.dsl.addCmd
import com.github.vacxe.googleplaycli.dsl.baseCmd
import com.github.vacxe.googleplaycli.dsl.cmd
import com.github.vacxe.googleplaycli.dsl.subcmd

fun main(args: Array<String>) {
    cmd("google-play-cli") {
        subcmd("apk") {
            baseCmd("list", "Lists all current APKs for the specified package and edit") { it.apksList(DefaultModel(packageName)) }
            addCmd { Commands.Apks.Upload() }
        }
        subcmd("bundles") {
            baseCmd("list") { it.bundlesList(DefaultModel(packageName)) }
            addCmd { Commands.Bundles.Upload() }
        }
        subcmd("deobfuscation-files") {
            addCmd { Commands.Deobfuscationfiles.Upload() }
        }
        subcmd("details") {
            baseCmd("get", "Fetches app details for this edit. This includes the default language and developer support contact information") { it.detailsGet(DefaultModel(packageName)) }
            addCmd { Commands.Details.Patch() }
            addCmd { Commands.Details.Update() }
        }
        subcmd("expansion-files") {
            addCmd { Commands.ExpansionFiles.Get() }
            addCmd { Commands.ExpansionFiles.Patch() }
            addCmd { Commands.ExpansionFiles.Update() }
            addCmd { Commands.ExpansionFiles.Upload() }
        }
        subcmd("images") {
            addCmd { Commands.Images.List() }
            addCmd { Commands.Images.Delete() }
            addCmd { Commands.Images.DeleteAll() }
            addCmd { Commands.Images.Upload() }
        }
        subcmd("listings") {
            baseCmd("deleteAll", "Deletes all localized listings from an edit") { it.listingsDeleteAll(DefaultModel(packageName)) }
            baseCmd("list", "Returns all of the localized store listings attached to this edit") { it.listingsList(DefaultModel(packageName)) }
            addCmd { Commands.Listings.Get() }
            addCmd { Commands.Listings.Delete() }
            addCmd { Commands.Listings.Update() }
            addCmd { Commands.Listings.Patch() }
        }
        subcmd("testers") {
            addCmd { Commands.Testers.Get() }
            addCmd { Commands.Testers.Update() }
            addCmd { Commands.Testers.Patch() }
        }
        subcmd("tracks") {
            addCmd { Commands.Tracks.Get() }
            baseCmd("list", "Lists all the track configurations for this edit") { it.tracksList(DefaultModel(packageName)) }
            addCmd { Commands.Tracks.Patch() }
            addCmd { Commands.Tracks.Update() }
        }
        subcmd("reviews") {
            addCmd { Commands.Reviews.List() }
            addCmd { Commands.Reviews.Get() }
            addCmd { Commands.Reviews.Reply() }
        }
        subcmd("internal-app-sharing-artifacts") {
            addCmd { Commands.Internalappsharingartifacts.UploadApk() }
            addCmd { Commands.Internalappsharingartifacts.UploadBundle() }
        }
        subcmd("orders") {
            addCmd { Commands.Orders.Refund() }
        }
    }.main(args)
}
