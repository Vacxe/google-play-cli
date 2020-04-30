package com.github.vacxe.googleplaycli.actions.orders.mapper

import com.github.vacxe.googleplaycli.actions.orders.configuration.OrdersRefundConfiguration
import com.github.vacxe.googleplaycli.actions.orders.model.OrdersRefundModel

class OrderRefundMapper {
    fun map(configuration: OrdersRefundConfiguration): OrdersRefundModel = OrdersRefundModel(
            packageName = configuration.packageName,
            orderId = configuration.orderId,
            revoke = configuration.revoke
    )
}