package com.github.vacxe.googleplaycli.actions.model

class ListingsUpdateModel(val packageName: String,
                          val editId: String?,
                          val language: String,
                          val fullDescription: String,
                          val shortDescription: String,
                          val title: String,
                          val video: String,
                          requestParameters: String?
): RequestModel(requestParameters)