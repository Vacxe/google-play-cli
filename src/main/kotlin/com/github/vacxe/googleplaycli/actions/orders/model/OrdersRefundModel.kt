package com.github.vacxe.googleplaycli.actions.orders.model

data class OrdersRefundModel(val packageName: String,
                             val orderId: String,
                             val revoke: Boolean)