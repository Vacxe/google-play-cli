package com.github.vacxe.googleplaycli.dsl

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.subcommands
import com.github.vacxe.googleplaycli.PlayStoreApi
import com.github.vacxe.googleplaycli.core.BaseCommand
import com.github.vacxe.googleplaycli.core.MetaCommand

fun cmd(name: String, configurationBlock: MetaCommand.() -> Unit): MetaCommand = MetaCommand(name).apply(configurationBlock)

fun MetaCommand.subcmd(name: String, configurationBlock: MetaCommand.() -> Unit) {
    subcommands(cmd(name, configurationBlock))
}

fun MetaCommand.baseCmd(name: String, description: String = "", block: BaseCommand.(PlayStoreApi) -> Any) {
    subcommands(
            object : BaseCommand(name = name, actionDescription = description) {
                override fun run(api: PlayStoreApi) = block(api)
            }
    )
}

fun MetaCommand.addCmd(block: () -> CliktCommand) {
    subcommands(block())
}