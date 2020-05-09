package com.github.vacxe.googleplaycli.actions.model

data class OrdersRefundModel(val packageName: String,
                             val orderId: String,
                             val revoke: Boolean)