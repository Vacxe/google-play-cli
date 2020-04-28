package com.github.vacxe.googleplaycli.actions.orders.models

data class OrdersRefundModel(val packageName: String,
                             val orderId: String,
                             val revoke: Boolean)