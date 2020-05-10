package com.github.vacxe.googleplaycli

import com.github.ajalt.clikt.parameters.options.*
import com.github.ajalt.clikt.parameters.types.*
import com.github.vacxe.googleplaycli.actions.model.*
import com.github.vacxe.googleplaycli.core.BaseCommand
import java.io.File

object Commands {
    object Apks {
        class Upload : BaseCommand(name = "upload", actionDescription = "") {
            private val apk: File by option("--apk", "-a", help = "Apk file path").file().required()
            override fun run(api: PlayStoreApi) = api.apksUpload(ApksUploadModel(packageName, apk))
        }
    }

    object Bundles {
        class Upload : BaseCommand(name = "upload", actionDescription = "Uploads a new Android App Bundle to this edit") {
            private val bundle: File by option("--bundle", "-b", help = "Bundle file path").file().required()
            private val ackBundleInstallationWarning: Boolean by option("--ack-bundle-installation-warning", "-a", help = "Must be set to true if the bundle installation may trigger a warning on user devices (for example, if installation size may be over a threshold, typically 100 MB).")
                    .flag(default = false)

            override fun run(api: PlayStoreApi) = api.bundlesUpload(BundlesUploadModel(packageName, bundle, ackBundleInstallationWarning))
        }
    }

    object Deobfuscationfiles {
        class Upload : BaseCommand(name = "upload", actionDescription = "Uploads the deobfuscation file of the specified APK. If a deobfuscation file already exists, it will be replaced") {
            private val deobfuscation: File by option("--deobfuscation", "-d", help = "Deobfuscation file path")
                    .file()
                    .required()

            val apkVersionCode: Int by option("--apk-version-code", "-v", help = "The version code of the APK whose deobfuscation file is being uploaded.")
                    .int().required()

            private val deobfuscationFileType: String by option("--deobfuscation-file-type", "-t", help = "Acceptable values are: proguard")
                    .choice("proguard")
                    .default("proguard")

            override fun run(api: PlayStoreApi) = api.deobfuscationFilesUpload(DeobfuscationfilesUploadModel(packageName, apkVersionCode, deobfuscation, deobfuscationFileType))
        }
    }

    object Details {
        class Patch : BaseCommand(name = "patch", actionDescription = "Updates app details for this edit") {
            private val contactEmail: String? by option("--contact-email", "-e", help = "The user-visible support email for this app")
            private val contactPhone: String? by option("--contact-phone", help = "The user-visible support telephone number for this app")
            private val contactWebsite: String? by option("--contact-website", "-w", help = "The user-visible website for this app")
            private val defaultLanguage: String? by option("--default-language", "-l", help = "Default language code, in BCP 47 format (eg \"en-US\").")

            override fun run(api: PlayStoreApi) = api.detailsPatch(DetailsPatchModel(packageName, contactEmail, contactPhone, contactWebsite, defaultLanguage))
        }

        class Update : BaseCommand(name = "update", actionDescription = "Updates app details for this edit") {
            private val contactEmail: String? by option("--contact-email", "-e", help = "The user-visible support email for this app")
            private val contactPhone: String? by option("--contact-phone", help = "The user-visible support telephone number for this app")
            private val contactWebsite: String? by option("--contact-website", "-w", help = "The user-visible website for this app")
            private val defaultLanguage: String? by option("--default-language", "-l", help = "Default language code, in BCP 47 format (eg \"en-US\").")

            override fun run(api: PlayStoreApi) = api.detailsUpdate(DetailsUpdateModel(packageName, contactEmail, contactPhone, contactWebsite, defaultLanguage))
        }
    }

    object ExpansionFiles {
        class Get : BaseCommand(name = "get", actionDescription = "Fetches the Expansion File configuration for the APK specified") {
            val apkVersionCode: Int by option("--apk-version-code", "-v", help = "The version code of the APK whose Expansion File configuration is being read or modified").int().required()
            private val expansionFileType: String by option("--expansion-file-type", "-t").choice("main", "patch").default("main")

            override fun run(api: PlayStoreApi) = api.expansionFilesGet(ExpansionFilesGetModel(packageName, apkVersionCode, expansionFileType))
        }

        class Patch : BaseCommand(name = "patch", actionDescription = "Updates the APK's Expansion File configuration to reference another APK's Expansion Files. To add a new Expansion File use the Upload method") {
            val apkVersionCode: Int by option("--apk-version-code", "-v", help = "The version code of the APK whose Expansion File configuration is being read or modified").int().required()
            private val expansionFileType: String by option("--expansion-file-type", "-t").choice("main", "patch").default("main")
            private val referencesVersion: Int? by option("--references-version", "-r").int()
            private val fileSize: Long? by option("--file-size", "-s").long()

            override fun run(api: PlayStoreApi) = api.expansionFilesPatch(ExpansionFilesPatchModel(packageName, apkVersionCode, expansionFileType, referencesVersion, fileSize))
        }

        class Update : BaseCommand(name = "update", actionDescription = "Updates the APK's Expansion File configuration to reference another APK's Expansion Files. To add a new Expansion File use the Upload method") {
            val apkVersionCode: Int by option("--apk-version-code", "-v", help = "The version code of the APK whose Expansion File configuration is being read or modified").int().required()
            private val expansionFileType: String by option("--expansion-file-type", "-t").choice("main", "patch").default("main")
            private val referencesVersion: Int by option("--references-version", "-r").int().default(0)
            private val fileSize: Long by option("--file-size", "-s").long().default(0L)

            override fun run(api: PlayStoreApi) = api.expansionFilesUpdate(ExpansionFilesUpdateModel(packageName, apkVersionCode, expansionFileType, referencesVersion, fileSize))
        }

        class Upload : BaseCommand(name = "upload", actionDescription = "Uploads and attaches a new Expansion File to the APK specified") {
            val apkVersionCode: Int by option("--apk-version-code", "-v", help = "The version code of the APK whose Expansion File configuration is being read or modified").int().required()
            private val expansionFileType: String by option("--expansion-file-type", "-t").choice("main", "patch").default("main")
            private val expansionFile: File by option("--expansion-file", "-f", help = "Expansion file patch").file().required()

            override fun run(api: PlayStoreApi) = api.expansionFilesUpload(ExpansionFilesUploadModel(packageName, apkVersionCode, expansionFileType, expansionFile))
        }
    }

    object Images {
        class List : BaseCommand(name = "list", actionDescription = "Lists all images for the specified language and image type") {
            private val imageType: String by option("--image-type", "-t")
                    .choice("featureGraphic", "icon", "phoneScreenshots", "promoGraphic", "sevenInchScreenshots", "tenInchScreenshots", "tvBanner", "tvScreenshots", "wearScreenshots").required()
            val language: String by option("--language", "-l", help = "The language code (a BCP-47 language tag) of the localized listing whose images you want to list. For example, to select Latin American Spanish, pass \"es-419\".").required()


            override fun run(api: PlayStoreApi) = api.imagesList(ImagesListModel(packageName, imageType, language))
        }

        class Delete : BaseCommand(name = "delete", actionDescription = "Deletes the image (specified by id) from the edit") {
            private val imageType: String by option("--image-type", "-t")
                    .choice("featureGraphic", "icon", "phoneScreenshots", "promoGraphic", "sevenInchScreenshots", "tenInchScreenshots", "tvBanner", "tvScreenshots", "wearScreenshots").required()
            val language: String by option("--language", "-l", help = "The language code (a BCP-47 language tag) of the localized listing whose images you want to list. For example, to select Latin American Spanish, pass \"es-419\".").required()
            private val imageId: String by option("--image-id", "-i").required()

            override fun run(api: PlayStoreApi) = api.imagesDelete(ImagesDeleteModel(packageName, imageType, language, imageId))
        }

        class DeleteAll : BaseCommand(name = "delete-all", actionDescription = "Deletes all images for the specified language and image type") {
            private val imageType: String by option("--image-type", "-t")
                    .choice("featureGraphic", "icon", "phoneScreenshots", "promoGraphic", "sevenInchScreenshots", "tenInchScreenshots", "tvBanner", "tvScreenshots", "wearScreenshots").required()
            val language: String by option("--language", "-l", help = "The language code (a BCP-47 language tag) of the localized listing whose images you want to list. For example, to select Latin American Spanish, pass \"es-419\".").required()

            override fun run(api: PlayStoreApi) = api.imagesDeleteAll(ImagesDeleteAllModel(packageName, imageType, language))
        }

        class Upload : BaseCommand(name = "upload", actionDescription = "Uploads a new image and adds it to the list of images for the specified language and image type") {
            private val image: File by option("--image", "-i", help = "Image file path").file().required()
            private val imageType: String by option("--image-type", "-t")
                    .choice("featureGraphic", "icon", "phoneScreenshots", "promoGraphic", "sevenInchScreenshots", "tenInchScreenshots", "tvBanner", "tvScreenshots", "wearScreenshots").required()
            val language: String by option("--language", "-l", help = "The language code (a BCP-47 language tag) of the localized listing whose images you want to list. For example, to select Latin American Spanish, pass \"es-419\".").required()

            override fun run(api: PlayStoreApi) = api.imagesUploadAll(ImagesUploadModel(packageName, imageType, language, image))
        }
    }

    object Listings {
        class Get : BaseCommand(name = "get", actionDescription = "Fetches information about a localized store listing") {
            val language: String by option("--language", "-l", help = "The language code (a BCP-47 language tag) of the localized listing to read or modify. For example, to select Latin American Spanish, pass \"es-419\".").required()

            override fun run(api: PlayStoreApi) = api.listingsGet(ListingsGetModel(packageName, language))
        }

        class Delete : BaseCommand(name = "delete", actionDescription = "Deletes the specified localized store listing from an edit") {
            val language: String by option("--language", "-l", help = "The language code (a BCP-47 language tag) of the localized listing to read or modify. For example, to select Latin American Spanish, pass \"es-419\".").required()

            override fun run(api: PlayStoreApi) = api.listingsDelete(ListingsDeleteModel(packageName, language))
        }

        class Update : BaseCommand(name = "update", actionDescription = "Creates or updates a localized store listing") {
            val language: String by option("--language", "-l", help = "The language code (a BCP-47 language tag) of the localized listing to read or modify. For example, to select Latin American Spanish, pass \"es-419\".").required()
            private val fullDescription: String by option("--full-description", "-d", help = "Full description of the app; this may be up to 4000 characters in length.").default("").validate { it.length <= 4000 }
            private val shortDescription: String by option("--short-description", "-s", help = "Short description of the app (previously known as promo text); this may be up to 80 characters in length.").default("").validate { it.length <= 80 }
            private val title: String by option("--title", "-t", help = "App's localized title.").default("")
            private val video: String by option("--video-url", "-v", help = "URL of a promotional YouTube video for the app.").default("")

            override fun run(api: PlayStoreApi) = api.listingsUpdate(ListingsUpdateModel(packageName, language, fullDescription, shortDescription, title, video))
        }

        class Patch : BaseCommand(name = "patch", actionDescription = "Creates or updates a localized store listing") {
            val language: String? by option("--language", "-l", help = "The language code (a BCP-47 language tag) of the localized listing to read or modify. For example, to select Latin American Spanish, pass \"es-419\".")
            private val fullDescription: String? by option("--full-description", "-d", help = "Full description of the app; this may be up to 4000 characters in length.").validate { it.length <= 4000 }
            private val shortDescription: String? by option("--short-description", "-s", help = "Short description of the app (previously known as promo text); this may be up to 80 characters in length.").validate { it.length <= 80 }
            private val title: String? by option("--title", "-t", help = "App's localized title.")
            private val video: String? by option("--video-url", "-v", help = "URL of a promotional YouTube video for the app.")

            override fun run(api: PlayStoreApi) = api.listingsPatch(ListingsPatchModel(packageName, language, fullDescription, shortDescription, title, video))
        }
    }

    object Testers {
        class Get : BaseCommand(name = "get") {
            private val track: String by option("--track", "-t").required()

            override fun run(api: PlayStoreApi) = api.testersGet(TestersGetModel(packageName, track))
        }

        class Update : BaseCommand(name = "update") {
            private val track: String by option("--track", "-t").required()
            private val googleGroups: List<String> by option("--google-group", "-g").multiple()
            private val googlePlusCommunities: List<String> by option("--google-plus-communities").multiple()

            override fun run(api: PlayStoreApi) = api.testersUpdate(TestersUpdateModel(packageName, track, googleGroups, googlePlusCommunities))
        }

        class Patch : BaseCommand(name = "patch") {
            private val track: String by option("--track", "-t").required()
            private val googleGroups: List<String> by option("--google-group", "-g").multiple()
            private val googlePlusCommunities: List<String> by option("--google-plus-communities").multiple()

            override fun run(api: PlayStoreApi) = api.testersPatch(TestersPatchModel(packageName, track, googleGroups, googlePlusCommunities))
        }
    }

    object Tracks {
        class Get : BaseCommand(name = "get", actionDescription = "Fetches the track configuration for the specified track type. Includes the APK version codes that are in this track") {
            private val track: String by option("--track", "-t").required()

            override fun run(api: PlayStoreApi) = api.tracksGet(TracksGetModel(packageName, track))
        }

        class Patch : BaseCommand(name = "patch", actionDescription = "Updates the track configuration for the specified track type") {
            private val track: String by option("--track", "-t").required()
            val apkVersionCode: Int by option("--apk-version-code", "-v", help = "The version code of the APK whose will be promoted to the track").int().required()
            private val userFraction: Double by option("--user-fraction", "-f", help = "Fraction of users who are eligible to receive the release. 0 < fraction < 1. To be set, release status must be \"inProgress\" or \"halted\".").double().default(1.0)

            override fun run(api: PlayStoreApi) = api.tracksPatch(TracksPatchModel(packageName, track, apkVersionCode, userFraction))
        }

        class Update : BaseCommand(name = "update", actionDescription = "Updates the track configuration for the specified track type") {
            private val track: String by option("--track", "-t").required()
            val apkVersionCode: Int by option("--apk-version-code", "-v", help = "The version code of the APK whose will be promoted to the track").int().required()
            private val userFraction: Double by option("--user-fraction", "-f", help = "Fraction of users who are eligible to receive the release. 0 < fraction < 1. To be set, release status must be \"inProgress\" or \"halted\".").double().default(1.0)

            override fun run(api: PlayStoreApi) = api.tracksUpdate(TracksUpdateModel(packageName, track, apkVersionCode, userFraction))
        }
    }

    object Reviews {
        class List : BaseCommand(name = "list", actionDescription = "Returns a list of reviews. Only reviews from last week will be returned") {
            private val maxResults: Long by option("--max-results", "-m").long().default(100L)
            private val startIndex: Long by option("--start-index", "-s").long().default(0L)
            private val translationLanguage: String by option("--translation-language", "-l").default("")
            private val token: String by option("--token", "-t").default("")

            override fun run(api: PlayStoreApi) = api.reviewsList(ReviewsListModel(packageName, maxResults, startIndex, token, translationLanguage))
        }

        class Get : BaseCommand(name = "get", actionDescription = "Returns a single review") {
            private val reviewId: String by option("--review-id", "-r").required()
            private val translationLanguage: String by option("--translation-language", "-l").default("")

            override fun run(api: PlayStoreApi) = api.reviewsGet(ReviewsGetModel(packageName, reviewId, translationLanguage))
        }

        class Reply : BaseCommand(name = "reply", actionDescription = "Reply to a single review, or update an existing reply") {
            private val reviewId: String by option("--review-id", "-r").required()
            private val replyText: String by option("--reply-text", "-a").required()

            override fun run(api: PlayStoreApi) = api.reviewsReply(ReviewsReplyModel(packageName, reviewId, replyText))
        }
    }

    object Internalappsharingartifacts {
        class UploadApk : BaseCommand(name = "upload-apk", actionDescription = "Uploads an APK to internal app sharing") {
            private val apk: File by option("--apk", "-a", help = "Apk file path").file().required()

            override fun run(api: PlayStoreApi) = api.internalappsharingartifactsUploadapk(InternalappsharingartifactsUploadapkModel(packageName, apk))
        }

        class UploadBundle : BaseCommand(name = "upload-bunble", actionDescription = "Uploads an app bundle to internal app sharing") {
            private val bundle: File by option("--bundle", "-b", help = "Bundle file path").file().required()

            override fun run(api: PlayStoreApi) = api.internalappsharingartifactsUploadbundle(InternalappsharingartifactsUploadbundleModel(packageName, bundle))
        }
    }

    object Orders {
        class Refund : BaseCommand(name = "refund", actionDescription = "Refund a user's subscription or in-app purchase order") {
            private val orderId: String by option("--order-id", "-o", help = "The order ID provided to the user when the subscription or in-app order was purchased").required()
            private val revoke: Boolean by option("--revoke", "-l", help = "Whether to revoke the purchased item. If set to true, access to the subscription or in-app item will be terminated immediately. If the item is a recurring subscription, all future payments will also be terminated. Consumed in-app items need to be handled by developer's app. (optional)").flag()

            override fun run(api: PlayStoreApi) = api.ordersRefund(OrdersRefundModel(packageName, orderId, revoke))
        }
    }
}
