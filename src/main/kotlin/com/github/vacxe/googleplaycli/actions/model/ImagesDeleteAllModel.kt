package com.github.vacxe.googleplaycli.actions.model

data class ImagesDeleteAllModel(val packageName: String,
                                val editId: String?,
                                val imageType: String,
                                val language: String)