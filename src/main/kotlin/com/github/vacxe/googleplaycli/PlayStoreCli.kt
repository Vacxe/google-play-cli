package com.github.vacxe.googleplaycli

import com.github.ajalt.clikt.core.CliktCommand
import com.github.vacxe.googleplaycli.dsl.addCmd
import com.github.vacxe.googleplaycli.dsl.cmd
import com.github.vacxe.googleplaycli.dsl.subcmd

fun main(args: Array<String>) {
    cmd("google-play-cli") {
        subcmd("apk") {
            addCmd { Commands.Apks.List() }
            addCmd { Commands.Apks.Upload() }
        }
        subcmd("bundles") {
            addCmd { Commands.Bundles.List() }
            addCmd { Commands.Bundles.Upload() }
        }
        subcmd("deobfuscation-files") {
            addCmd { Commands.Deobfuscationfiles.Upload() }
        }
        subcmd("details") {
            addCmd { Commands.Details.Get() }
            addCmd { Commands.Details.Patch() }
            addCmd { Commands.Details.Update() }
        }
        subcmd("device-tier-configs") {
            addCmd { Commands.DeviceTiersConfigs.Get() }
            addCmd { Commands.DeviceTiersConfigs.List() }
        }
        subcmd("edit") {
            addCmd { Commands.Edits.Create() }
            addCmd { Commands.Edits.Validate() }
            addCmd { Commands.Edits.Commit() }
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
            addCmd { Commands.Listings.DeleteAll() }
            addCmd { Commands.Listings.List() }
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
            addCmd { Commands.Tracks.List() }
            addCmd { Commands.Tracks.Patch() }
            addCmd { Commands.Tracks.Update() }
        }
        subcmd("reviews") {
            addCmd { Commands.Reviews.List() }
            addCmd { Commands.Reviews.Get() }
            addCmd { Commands.Reviews.Reply() }
        }
        subcmd("internal-app-sharing-artifacts") {
            addCmd { Commands.InternalAppSharingArtifacts.UploadApk() }
            addCmd { Commands.InternalAppSharingArtifacts.UploadBundle() }
        }
        subcmd("orders") {
            addCmd { Commands.Orders.Refund() }
        }
        subcmd("in-app-products") {
            addCmd { Commands.Inappproducts.Delete() }
            addCmd { Commands.Inappproducts.Get() }
            addCmd { Commands.Inappproducts.Insert() }
            addCmd { Commands.Inappproducts.List() }
            addCmd { Commands.Inappproducts.Patch() }
            addCmd { Commands.Inappproducts.Update() }
        }
        addCmd {
            object : CliktCommand(name = "version", help = "Library version code") {
                override fun run() {
                    println("0.4.6")
                }
            }
        }
    }.main(args)
}
