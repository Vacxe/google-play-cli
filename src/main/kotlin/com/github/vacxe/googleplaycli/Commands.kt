package com.github.vacxe.googleplaycli

import com.github.ajalt.clikt.parameters.options.*
import com.github.ajalt.clikt.parameters.types.*
import com.github.vacxe.googleplaycli.actions.model.*
import com.github.vacxe.googleplaycli.core.BaseCommand
import com.github.vacxe.googleplaycli.core.EditCommand
import java.io.File
import java.nio.file.Path

object Commands {
    object Apks {
        class List : EditCommand(name = "list", actionDescription = "Lists all current APKs for the specified package and edit") {
            override fun run(api: PlayStoreApi) = api.apksList(EditModel(packageName, editId, parameters))
        }

        class Upload : EditCommand(name = "upload", actionDescription = "") {
            private val apk: File by option("--apk", "-a", help = "Apk file path").file().required()
            override fun run(api: PlayStoreApi) = api.apksUpload(ApksUploadModel(packageName, editId, apk, parameters))
        }
    }

    object Bundles {
        class List : EditCommand(name = "list", actionDescription = "List of all Android App Bundle") {
            override fun run(api: PlayStoreApi) = api.bundlesList(EditModel(packageName, editId, parameters))
        }

        class Upload : EditCommand(name = "upload", actionDescription = "Uploads a new Android App Bundle to this edit") {
            private val bundle: File by option("--bundle", "-b", help = "Bundle file path").file().required()
            private val ackBundleInstallationWarning: Boolean by option("--ack-bundle-installation-warning", "-a", help = "Must be set to true if the bundle installation may trigger a warning on user devices (for example, if installation size may be over a threshold, typically 100 MB).")
                    .flag(default = false)

            override fun run(api: PlayStoreApi) = api.bundlesUpload(BundlesUploadModel(packageName, editId, bundle, ackBundleInstallationWarning, parameters))
        }
    }

    object Deobfuscationfiles {
        class Upload : EditCommand(name = "upload", actionDescription = "Uploads the deobfuscation file of the specified APK. If a deobfuscation file already exists, it will be replaced") {
            private val deobfuscation: File by option("--deobfuscation", "-d", help = "Deobfuscation file path")
                    .file()
                    .required()

            val apkVersionCode: Int by option("--apk-version-code", "-v", help = "The version code of the APK whose deobfuscation file is being uploaded.")
                    .int().required()

            private val deobfuscationFileType: String by option("--deobfuscation-file-type", "-t", help = "Acceptable values are: proguard")
                    .choice("proguard")
                    .default("proguard")

            override fun run(api: PlayStoreApi) = api.deobfuscationFilesUpload(DeobfuscationfilesUploadModel(packageName, editId, apkVersionCode, deobfuscation, deobfuscationFileType, parameters))
        }
    }

    object Details {
        class Get : EditCommand(name = "get", actionDescription = "Fetches app details for this edit. This includes the default language and developer support contact information") {
            override fun run(api: PlayStoreApi) = api.detailsGet(EditModel(packageName, editId, parameters))
        }

        class Patch : EditCommand(name = "patch", actionDescription = "Updates app details for this edit") {
            private val contactEmail: String? by option("--contact-email", "-e", help = "The user-visible support email for this app")
            private val contactPhone: String? by option("--contact-phone", help = "The user-visible support telephone number for this app")
            private val contactWebsite: String? by option("--contact-website", "-w", help = "The user-visible website for this app")
            private val defaultLanguage: String? by option("--default-language", "-l", help = "Default language code, in BCP 47 format (eg \"en-US\").")

            override fun run(api: PlayStoreApi) = api.detailsPatch(DetailsPatchModel(packageName, editId, contactEmail, contactPhone, contactWebsite, defaultLanguage, parameters))
        }

        class Update : EditCommand(name = "update", actionDescription = "Updates app details for this edit") {
            private val contactEmail: String? by option("--contact-email", "-e", help = "The user-visible support email for this app")
            private val contactPhone: String? by option("--contact-phone", help = "The user-visible support telephone number for this app")
            private val contactWebsite: String? by option("--contact-website", "-w", help = "The user-visible website for this app")
            private val defaultLanguage: String? by option("--default-language", "-l", help = "Default language code, in BCP 47 format (eg \"en-US\").")

            override fun run(api: PlayStoreApi) = api.detailsUpdate(DetailsUpdateModel(packageName, editId, contactEmail, contactPhone, contactWebsite, defaultLanguage, parameters))
        }
    }

    object Edits {
        class Create : BaseCommand(name = "create", actionDescription = "Create a new edit") {
            override fun run(api: PlayStoreApi) = api.editCreate(DefaultModel(packageName, parameters))
        }

        class Commit : EditCommand(name = "commit", actionDescription = "") {
            override fun run(api: PlayStoreApi) = api.editCommit(EditModel(packageName, editId, parameters))
        }

        class Validate : EditCommand(name = "validate", actionDescription = "") {
            override fun run(api: PlayStoreApi) = api.editValidate(EditModel(packageName, editId, parameters))
        }
    }

    object ExpansionFiles {
        class Get : EditCommand(name = "get", actionDescription = "Fetches the Expansion File configuration for the APK specified") {
            val apkVersionCode: Int by option("--apk-version-code", "-v", help = "The version code of the APK whose Expansion File configuration is being read or modified").int().required()
            private val expansionFileType: String by option("--expansion-file-type", "-t").choice("main", "patch").default("main")

            override fun run(api: PlayStoreApi) = api.expansionFilesGet(ExpansionFilesGetModel(packageName, editId, apkVersionCode, expansionFileType, parameters))
        }

        class Patch : EditCommand(name = "patch", actionDescription = "Updates the APK's Expansion File configuration to reference another APK's Expansion Files. To add a new Expansion File use the Upload method") {
            val apkVersionCode: Int by option("--apk-version-code", "-v", help = "The version code of the APK whose Expansion File configuration is being read or modified").int().required()
            private val expansionFileType: String by option("--expansion-file-type", "-t").choice("main", "patch").default("main")
            private val referencesVersion: Int? by option("--references-version", "-r").int()
            private val fileSize: Long? by option("--file-size", "-s").long()

            override fun run(api: PlayStoreApi) = api.expansionFilesPatch(ExpansionFilesPatchModel(packageName, editId, apkVersionCode, expansionFileType, referencesVersion, fileSize, parameters))
        }

        class Update : EditCommand(name = "update", actionDescription = "Updates the APK's Expansion File configuration to reference another APK's Expansion Files. To add a new Expansion File use the Upload method") {
            val apkVersionCode: Int by option("--apk-version-code", "-v", help = "The version code of the APK whose Expansion File configuration is being read or modified").int().required()
            private val expansionFileType: String by option("--expansion-file-type", "-t").choice("main", "patch").default("main")
            private val referencesVersion: Int by option("--references-version", "-r").int().default(0)
            private val fileSize: Long by option("--file-size", "-s").long().default(0L)

            override fun run(api: PlayStoreApi) = api.expansionFilesUpdate(ExpansionFilesUpdateModel(packageName, editId, apkVersionCode, expansionFileType, referencesVersion, fileSize, parameters))
        }

        class Upload : EditCommand(name = "upload", actionDescription = "Uploads and attaches a new Expansion File to the APK specified") {
            val apkVersionCode: Int by option("--apk-version-code", "-v", help = "The version code of the APK whose Expansion File configuration is being read or modified").int().required()
            private val expansionFileType: String by option("--expansion-file-type", "-t").choice("main", "patch").default("main")
            private val expansionFile: File by option("--expansion-file", "-f", help = "Expansion file patch").file().required()

            override fun run(api: PlayStoreApi) = api.expansionFilesUpload(ExpansionFilesUploadModel(packageName, editId, apkVersionCode, expansionFileType, expansionFile, parameters))
        }
    }

    object Images {
        class List : EditCommand(name = "list", actionDescription = "Lists all images for the specified language and image type") {
            private val imageType: String by option("--image-type", "-t")
                    .choice("featureGraphic", "icon", "phoneScreenshots", "promoGraphic", "sevenInchScreenshots", "tenInchScreenshots", "tvBanner", "tvScreenshots", "wearScreenshots").required()
            val language: String by option("--language", "-l", help = "The language code (a BCP-47 language tag) of the localized listing whose images you want to list. For example, to select Latin American Spanish, pass \"es-419\".").required()


            override fun run(api: PlayStoreApi) = api.imagesList(ImagesListModel(packageName, editId, imageType, language, parameters))
        }

        class Delete : EditCommand(name = "delete", actionDescription = "Deletes the image (specified by id) from the edit") {
            private val imageType: String by option("--image-type", "-t")
                    .choice("featureGraphic", "icon", "phoneScreenshots", "promoGraphic", "sevenInchScreenshots", "tenInchScreenshots", "tvBanner", "tvScreenshots", "wearScreenshots").required()
            val language: String by option("--language", "-l", help = "The language code (a BCP-47 language tag) of the localized listing whose images you want to list. For example, to select Latin American Spanish, pass \"es-419\".").required()
            private val imageId: String by option("--image-id", "-i").required()

            override fun run(api: PlayStoreApi) = api.imagesDelete(ImagesDeleteModel(packageName, editId, imageType, language, imageId, parameters))
        }

        class DeleteAll : EditCommand(name = "delete-all", actionDescription = "Deletes all images for the specified language and image type") {
            private val imageType: String by option("--image-type", "-t")
                    .choice("featureGraphic", "icon", "phoneScreenshots", "promoGraphic", "sevenInchScreenshots", "tenInchScreenshots", "tvBanner", "tvScreenshots", "wearScreenshots").required()
            val language: String by option("--language", "-l", help = "The language code (a BCP-47 language tag) of the localized listing whose images you want to list. For example, to select Latin American Spanish, pass \"es-419\".").required()

            override fun run(api: PlayStoreApi) = api.imagesDeleteAll(ImagesDeleteAllModel(packageName, editId, imageType, language, parameters))
        }

        class Upload : EditCommand(name = "upload", actionDescription = "Uploads a new image and adds it to the list of images for the specified language and image type") {
            private val image: File by option("--image", "-i", help = "Image file path").file().required()
            private val imageType: String by option("--image-type", "-t")
                    .choice("featureGraphic", "icon", "phoneScreenshots", "promoGraphic", "sevenInchScreenshots", "tenInchScreenshots", "tvBanner", "tvScreenshots", "wearScreenshots").required()
            val language: String by option("--language", "-l", help = "The language code (a BCP-47 language tag) of the localized listing whose images you want to list. For example, to select Latin American Spanish, pass \"es-419\".").required()

            override fun run(api: PlayStoreApi) = api.imagesUploadAll(ImagesUploadModel(packageName, editId, imageType, language, image, parameters))
        }
    }

    object Listings {
        class DeleteAll : EditCommand(name = "deleteAll", actionDescription = "Deletes all localized listings from an edit") {
            override fun run(api: PlayStoreApi) = api.listingsDeleteAll(EditModel(packageName, editId, parameters))
        }

        class List : EditCommand(name = "list", actionDescription = "Returns all of the localized store listings attached to this edit") {
            override fun run(api: PlayStoreApi) = api.listingsList(EditModel(packageName, editId, parameters))
        }

        class Get : EditCommand(name = "get", actionDescription = "Fetches information about a localized store listing") {
            val language: String by option("--language", "-l", help = "The language code (a BCP-47 language tag) of the localized listing to read or modify. For example, to select Latin American Spanish, pass \"es-419\".").required()

            override fun run(api: PlayStoreApi) = api.listingsGet(ListingsGetModel(packageName, editId, language, parameters))
        }

        class Delete : EditCommand(name = "delete", actionDescription = "Deletes the specified localized store listing from an edit") {
            val language: String by option("--language", "-l", help = "The language code (a BCP-47 language tag) of the localized listing to read or modify. For example, to select Latin American Spanish, pass \"es-419\".").required()

            override fun run(api: PlayStoreApi) = api.listingsDelete(ListingsDeleteModel(packageName, editId, language, parameters))
        }

        class Update : EditCommand(name = "update", actionDescription = "Creates or updates a localized store listing") {
            val language: String by option("--language", "-l", help = "The language code (a BCP-47 language tag) of the localized listing to read or modify. For example, to select Latin American Spanish, pass \"es-419\".").required()
            private val fullDescription: String by option("--full-description", "-d", help = "Full description of the app; this may be up to 4000 characters in length.").default("").validate { it.length <= 4000 }
            private val shortDescription: String by option("--short-description", "-s", help = "Short description of the app (previously known as promo text); this may be up to 80 characters in length.").default("").validate { it.length <= 80 }
            private val title: String by option("--title", "-t", help = "App's localized title.").default("")
            private val video: String by option("--video-url", "-v", help = "URL of a promotional YouTube video for the app.").default("")

            override fun run(api: PlayStoreApi) = api.listingsUpdate(ListingsUpdateModel(packageName, editId, language, fullDescription, shortDescription, title, video, parameters))
        }

        class Patch : EditCommand(name = "patch", actionDescription = "Creates or updates a localized store listing") {
            val language: String? by option("--language", "-l", help = "The language code (a BCP-47 language tag) of the localized listing to read or modify. For example, to select Latin American Spanish, pass \"es-419\".")
            private val fullDescription: String? by option("--full-description", "-d", help = "Full description of the app; this may be up to 4000 characters in length.").validate { it.length <= 4000 }
            private val shortDescription: String? by option("--short-description", "-s", help = "Short description of the app (previously known as promo text); this may be up to 80 characters in length.").validate { it.length <= 80 }
            private val title: String? by option("--title", "-t", help = "App's localized title.")
            private val video: String? by option("--video-url", "-v", help = "URL of a promotional YouTube video for the app.")

            override fun run(api: PlayStoreApi) = api.listingsPatch(ListingsPatchModel(packageName, editId, language, fullDescription, shortDescription, title, video, parameters))
        }
    }

    object Testers {
        class Get : EditCommand(name = "get") {
            private val track: String by option("--track", "-t").required()

            override fun run(api: PlayStoreApi) = api.testersGet(TestersGetModel(packageName, editId, track, parameters))
        }

        class Update : EditCommand(name = "update") {
            private val track: String by option("--track", "-t").required()
            private val googleGroups: List<String> by option("--google-group", "-g").multiple()

            override fun run(api: PlayStoreApi) = api.testersUpdate(TestersUpdateModel(packageName, editId, track, googleGroups, parameters))
        }

        class Patch : EditCommand(name = "patch") {
            private val track: String by option("--track", "-t").required()
            private val googleGroups: List<String> by option("--google-group", "-g").multiple()
            private val googlePlusCommunities: List<String> by option("--google-plus-communities").multiple()

            override fun run(api: PlayStoreApi) = api.testersPatch(TestersPatchModel(packageName, editId, track, googleGroups, googlePlusCommunities, parameters))
        }
    }

    object Tracks {
        class List : EditCommand(name = "list", actionDescription = "Lists all the track configurations for this edit") {
            override fun run(api: PlayStoreApi) = api.tracksList(EditModel(packageName, editId, parameters))
        }

        class Get : EditCommand(name = "get", actionDescription = "Fetches the track configuration for the specified track type. Includes the APK version codes that are in this track") {
            private val track: String by option("--track", "-t").required()

            override fun run(api: PlayStoreApi) = api.tracksGet(TracksGetModel(packageName, editId, track, parameters))
        }

        class Patch : EditCommand(name = "patch", actionDescription = "Updates the track configuration for the specified track type") {
            private val track: String by option("--track", "-t").required()
            val apkVersionCode: Int by option("--apk-version-code", "-v", help = "The version code of the APK whose will be promoted to the track").int().required()
            private val userFraction: Double by option("--user-fraction", "-f", help = "Fraction of users who are eligible to receive the release. 0 < fraction < 1. To be set, release status must be \"inProgress\" or \"halted\".").double().default(1.0)

            override fun run(api: PlayStoreApi) = api.tracksPatch(TracksPatchModel(packageName, editId, track, apkVersionCode, userFraction, parameters))
        }

        class Update : EditCommand(name = "update", actionDescription = "Updates the track configuration for the specified track type") {
            private val track: String by option("--track", "-t").required()
            val apkVersionCode: Int by option("--apk-version-code", "-v", help = "The version code of the APK whose will be promoted to the track").int().required()
            private val userFraction: Double by option("--user-fraction", "-f", help = "Fraction of users who are eligible to receive the release. 0 < fraction < 1. To be set, release status must be \"inProgress\" or \"halted\".").double().default(1.0)

            override fun run(api: PlayStoreApi) = api.tracksUpdate(TracksUpdateModel(packageName, editId, track, apkVersionCode, userFraction, parameters))
        }
    }

    object Reviews {
        class List : EditCommand(name = "list", actionDescription = "Returns a list of reviews. Only reviews from last week will be returned") {
            private val maxResults: Long by option("--max-results", "-m").long().default(100L)
            private val startIndex: Long by option("--start-index", "-s").long().default(0L)
            private val translationLanguage: String by option("--translation-language", "-l").default("")
            private val token: String by option("--token", "-t").default("")

            override fun run(api: PlayStoreApi) = api.reviewsList(ReviewsListModel(packageName, editId, maxResults, startIndex, token, translationLanguage, parameters))
        }

        class Get : EditCommand(name = "get", actionDescription = "Returns a single review") {
            private val reviewId: String by option("--review-id", "-r").required()
            private val translationLanguage: String by option("--translation-language", "-l").default("")

            override fun run(api: PlayStoreApi) = api.reviewsGet(ReviewsGetModel(packageName, editId, reviewId, translationLanguage, parameters))
        }

        class Reply : EditCommand(name = "reply", actionDescription = "Reply to a single review, or update an existing reply") {
            private val reviewId: String by option("--review-id", "-r").required()
            private val replyText: String by option("--reply-text", "-a").required()

            override fun run(api: PlayStoreApi) = api.reviewsReply(ReviewsReplyModel(packageName, editId, reviewId, replyText, parameters))
        }
    }

    object Internalappsharingartifacts {
        class UploadApk : BaseCommand(name = "upload-apk", actionDescription = "Uploads an APK to internal app sharing") {
            private val apk: File by option("--apk", "-a", help = "Apk file path").file().required()

            override fun run(api: PlayStoreApi) = api.internalappsharingartifactsUploadapk(InternalappsharingartifactsUploadapkModel(packageName, apk, parameters))
        }

        class UploadBundle : BaseCommand(name = "upload-bunble", actionDescription = "Uploads an app bundle to internal app sharing") {
            private val bundle: File by option("--bundle", "-b", help = "Bundle file path").file().required()

            override fun run(api: PlayStoreApi) = api.internalappsharingartifactsUploadbundle(InternalappsharingartifactsUploadbundleModel(packageName, bundle, parameters))
        }
    }

    object Orders {
        class Refund : EditCommand(name = "refund", actionDescription = "Refund a user's subscription or in-app purchase order") {
            private val orderId: String by option("--order-id", "-o", help = "The order ID provided to the user when the subscription or in-app order was purchased").required()
            private val revoke: Boolean by option("--revoke", "-l", help = "Whether to revoke the purchased item. If set to true, access to the subscription or in-app item will be terminated immediately. If the item is a recurring subscription, all future payments will also be terminated. Consumed in-app items need to be handled by developer's app. (optional)").flag()

            override fun run(api: PlayStoreApi) = api.ordersRefund(OrdersRefundModel(packageName, editId, orderId, revoke, parameters))
        }
    }

    object Inappproducts {

        class Delete : BaseCommand(name = "delete", actionDescription = "Delete an in-app product for an app") {
            private val sku: String by option("--sku", "-s", help = "Unique identifier for the in-app product").required()

            override fun run(api: PlayStoreApi) = api.inappproductsDelete(InappproductsDeleteModel(packageName, sku, parameters))
        }

        class Get : BaseCommand(name = "get", actionDescription = "Returns information about the in-app product specified") {
            private val sku: String by option("--sku", "-s", help = "Unique identifier for the in-app product").required()

            override fun run(api: PlayStoreApi) = api.inappproductsGet(InappproductsGetModel(packageName, sku, parameters))
        }

        class Insert : BaseCommand(name = "insert", actionDescription = "Creates a new in-app product for an app") {
            private val jsonPath: Path by option("--file", "-f", help = "Json file path").path(mustExist = true, canBeDir = false).required()
            private val autoConvertMissingPrices: Boolean by option("--auto-convert-missing-prices", "-a", help = """
                        If true the prices for all regions targeted by the parent app that don't have a price specified for 
                        this in-app product will be auto converted to the target currency based on the default price. 
                        Defaults to false. (optional)
                        """.trimIndent()
            ).flag()

            override fun run(api: PlayStoreApi) = api.inappproductsInsert(InappproductsInsertModel(packageName, jsonPath, autoConvertMissingPrices, parameters))
        }

        class List : BaseCommand(name = "list", actionDescription = "List all the in-app products for an Android app, both subscriptions and managed in-app products") {
            override fun run(api: PlayStoreApi) = api.inappproductsList(DefaultModel(packageName, parameters))
        }

        class Patch : BaseCommand(name = "patch", actionDescription = "Updates the details of an in-app product. This method supports patch semantics") {
            private val sku: String by option("--sku", "-s", help = "Unique identifier for the in-app product").required()
            private val jsonPath: Path by option("--file", "-f", help = "Json file path").path(mustExist = true, canBeDir = false).required()
            private val autoConvertMissingPrices: Boolean by option("--auto-convert-missing-prices", "-a", help = """
                        If true the prices for all regions targeted by the parent app that don't have a price specified for 
                        this in-app product will be auto converted to the target currency based on the default price. 
                        Defaults to false. (optional)
                        """.trimIndent()
            ).flag()

            override fun run(api: PlayStoreApi) = api.inappproductsPatch(InappproductsPatchModel(packageName, sku, jsonPath, autoConvertMissingPrices, parameters))
        }

        class Update : BaseCommand(name = "update", actionDescription = "Updates the details of an in-app product") {
            private val sku: String by option("--sku", "-s", help = "Unique identifier for the in-app product").required()
            private val jsonPath: Path by option("--file", "-f", help = "Json file path").path(mustExist = true, canBeDir = false).required()
            private val autoConvertMissingPrices: Boolean by option("--auto-convert-missing-prices", "-a", help = """
                        If true the prices for all regions targeted by the parent app that don't have a price specified for 
                        this in-app product will be auto converted to the target currency based on the default price. 
                        Defaults to false. (optional)
                        """.trimIndent()
            ).flag()

            override fun run(api: PlayStoreApi) = api.inappproductsUpdate(InappproductsUpdateModel(packageName, sku, jsonPath, autoConvertMissingPrices, parameters))
        }
    }
}
