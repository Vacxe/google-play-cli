package com.github.vacxe.googleplaycli

import com.github.vacxe.googleplaycli.actions.apks.configuration.ApksListConfiguration
import com.github.vacxe.googleplaycli.actions.apks.configuration.ApksUploadConfiguration
import com.github.vacxe.googleplaycli.actions.apks.mappers.ApksListMapper
import com.github.vacxe.googleplaycli.actions.apks.mappers.ApksUploadMapper
import com.github.vacxe.googleplaycli.actions.bundles.configuration.BundlesListConfiguration
import com.github.vacxe.googleplaycli.actions.bundles.configuration.BundlesUploadConfiguration
import com.github.vacxe.googleplaycli.actions.bundles.mappers.BundlesListMapper
import com.github.vacxe.googleplaycli.actions.bundles.mappers.BundlesUploadMapper
import com.github.vacxe.googleplaycli.actions.deobfuscationfiles.configuration.DeobfuscationfilesUploadConfiguration
import com.github.vacxe.googleplaycli.actions.deobfuscationfiles.mapper.DeobfuscationfilesUploadMapper
import com.github.vacxe.googleplaycli.actions.tracks.configuration.TracksGetConfiguration
import com.github.vacxe.googleplaycli.actions.tracks.configuration.TracksListConfiguration
import com.github.vacxe.googleplaycli.actions.tracks.configuration.TracksPatchConfiguration
import com.github.vacxe.googleplaycli.actions.tracks.configuration.TracksUpdateConfiguration
import com.github.vacxe.googleplaycli.actions.tracks.mappers.TracksGetMapper
import com.github.vacxe.googleplaycli.actions.tracks.mappers.TracksListMapper
import com.github.vacxe.googleplaycli.actions.tracks.mappers.TracksPatchMapper
import com.github.vacxe.googleplaycli.actions.tracks.mappers.TracksUpdateMapper
import com.google.api.services.androidpublisher.model.*
import com.xenomachina.argparser.ArgParser

object Commands {

    /**
     * Url: https://developers.google.com/android-publisher/api-ref/edits/apks
     */
    object Apks {
        /**
         * Url: https://developers.google.com/android-publisher/api-ref/edits/apks/list
         */
        fun list(args: Array<String>): ApksListResponse {
            ArgParser(args).parseInto(::ApksListConfiguration).run {
                val manager = PlayStoreCli(serviceAccountJson, packageName)
                return ApksListMapper().map(this).let { manager.apksList(it) }
            }
        }

        /**
         * Url: https://developers.google.com/android-publisher/api-ref/edits/apks/upload
         */
        fun upload(args: Array<String>): Apk {
            ArgParser(args).parseInto(::ApksUploadConfiguration).run {
                val manager = PlayStoreCli(serviceAccountJson, packageName)
                return ApksUploadMapper().map(this).let { manager.apksUpload(it) }
            }
        }
    }

    /**
     * Url: https://developers.google.com/android-publisher/api-ref/edits/bundles
     */
    object Bundles {
        /**
         * Url: https://developers.google.com/android-publisher/api-ref/edits/bundles/list
         */
        fun list(args: Array<String>): BundlesListResponse {
            ArgParser(args).parseInto(::BundlesListConfiguration).run {
                val manager = PlayStoreCli(serviceAccountJson, packageName)
                return BundlesListMapper().map(this).let { manager.bundlesList(it) }
            }
        }

        /**
         * Url: https://developers.google.com/android-publisher/api-ref/edits/bundles/upload
         */
        fun upload(args: Array<String>): Bundle {
            ArgParser(args).parseInto(::BundlesUploadConfiguration).run {
                val manager = PlayStoreCli(serviceAccountJson, packageName)
                return BundlesUploadMapper().map(this).let { manager.bundlesUpload(it) }
            }
        }
    }

    /**
     * Url: https://developers.google.com/android-publisher/api-ref/edits/deobfuscationfiles
     */
    object Deobfuscationfiles {

        /**
         * Url: https://developers.google.com/android-publisher/api-ref/edits/deobfuscationfiles/upload
         */
        fun upload(args: Array<String>): DeobfuscationFilesUploadResponse {
            ArgParser(args).parseInto(::DeobfuscationfilesUploadConfiguration).run {
                val manager = PlayStoreCli(serviceAccountJson, packageName)
                return DeobfuscationfilesUploadMapper().map(this).let { manager.deobfuscationFilesUpload(it) }
            }
        }
    }

    /**
     * https://developers.google.com/android-publisher/api-ref/edits/tracks
     */
    object Tracks {

        /**
         * Url: https://developers.google.com/android-publisher/api-ref/edits/tracks/get
         */
        fun get(args: Array<String>): Track {
            ArgParser(args).parseInto(::TracksGetConfiguration).run {
                val manager = PlayStoreCli(serviceAccountJson, packageName)
                return TracksGetMapper().map(this).let { manager.tracksGet(it) }
            }
        }

        /**
         * Url: https://developers.google.com/android-publisher/api-ref/edits/tracks/list
         */
        fun list(args: Array<String>): TracksListResponse {
            ArgParser(args).parseInto(::TracksListConfiguration).run {
                val manager = PlayStoreCli(serviceAccountJson, packageName)
                return TracksListMapper().map(this).let { manager.tracksList(it) }
            }
        }

        /**
         * Url: https://developers.google.com/android-publisher/api-ref/edits/tracks/patch
         */
        fun patch(args: Array<String>): Track {
            ArgParser(args).parseInto(::TracksPatchConfiguration).run {
                val manager = PlayStoreCli(serviceAccountJson, packageName)
                return TracksPatchMapper().map(this).let { manager.tracksPatch(it) }
            }
        }

        /**
         * Url: https://developers.google.com/android-publisher/api-ref/edits/tracks/update
         */
        fun update(args: Array<String>): Track {
            ArgParser(args).parseInto(::TracksUpdateConfiguration).run {
                val manager = PlayStoreCli(serviceAccountJson, packageName)
                return TracksUpdateMapper().map(this).let { manager.tracksUpdate(it) }
            }
        }
    }
}
