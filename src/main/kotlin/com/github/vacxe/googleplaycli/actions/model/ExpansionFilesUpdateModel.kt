package com.github.vacxe.googleplaycli.actions.model

class ExpansionFilesUpdateModel(val packageName: String,
                                val editId: String?,
                                val apkVersionCode: Int,
                                val expansionFileType: String,
                                val referencesVersion: Int,
                                val fileSize: Long)