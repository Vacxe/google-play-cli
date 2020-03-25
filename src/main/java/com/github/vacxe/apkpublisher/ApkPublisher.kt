package com.github.vacxe.apkpublisher

import com.xenomachina.argparser.mainBody
import java.util.*

fun main(args: Array<String>): Unit = mainBody {
    val command = ArrayDeque<String>().apply { addAll(args) }
    when (command.pollFirst()) {
        "upload" -> {
            when (command.pollFirst()) {
                "apk" -> Commands.uploadApk(command.toTypedArray())
            }
        }
    }
}
