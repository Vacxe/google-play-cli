package com.github.vacxe.googleplaycli.actions.orders.configuration

import com.github.vacxe.googleplaycli.core.BaseConfiguration
import com.xenomachina.argparser.ArgParser
import com.xenomachina.argparser.default

class OrdersRefundConfiguration(parser: ArgParser) : BaseConfiguration(parser) {
    val orderId: String by parser
            .storing("--orderId", "-o")
            .default { "" }

    val revoke: Boolean by parser
            .storing("--revoke", "-l", help = "Whether to revoke the purchased item. If set to true, access to the subscription or in-app item will be terminated immediately. If the item is a recurring subscription, all future payments will also be terminated. Consumed in-app items need to be handled by developer's app. (optional)") { this.toBoolean()}
            .default { false }
}
