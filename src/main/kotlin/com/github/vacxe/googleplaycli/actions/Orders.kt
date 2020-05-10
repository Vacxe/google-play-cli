package com.github.vacxe.googleplaycli.actions

import com.github.vacxe.googleplaycli.actions.model.OrdersRefundModel

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