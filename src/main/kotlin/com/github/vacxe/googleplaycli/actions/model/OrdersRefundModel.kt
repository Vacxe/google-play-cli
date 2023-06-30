package com.github.vacxe.googleplaycli.actions.model

class OrdersRefundModel(val packageName: String,
                             val editId: String?,
                             val orderId: String,
                             val revoke: Boolean,
                             requestParameters: String?
): RequestModel(requestParameters)