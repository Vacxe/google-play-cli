package com.github.vacxe.googleplaycli.actions.orders

import com.github.vacxe.googleplaycli.actions.BaseAction
import com.github.vacxe.googleplaycli.actions.orders.model.OrdersRefundModel

interface Orders : BaseAction {
    fun ordersRefund(model: OrdersRefundModel): Void {
        return androidPublisher.Orders()
                .refund(model.packageName, model.orderId)
                .apply {
                    revoke = model.revoke
                }
                .execute()
    }
}