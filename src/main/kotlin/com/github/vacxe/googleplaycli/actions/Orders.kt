package com.github.vacxe.googleplaycli.actions

import com.github.vacxe.googleplaycli.actions.model.OrdersRefundModel

interface Orders : BaseAction {
    fun ordersRefund(model: OrdersRefundModel): Void {
        return androidPublisher.orders()
            .refund(model.packageName, model.orderId)
            .apply {
                revoke = model.revoke
            }
            .apply {
                model.parameters.forEach { (key, value) -> set(key, value) }
            }
            .execute()
    }
}
