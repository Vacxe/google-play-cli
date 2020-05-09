package com.github.vacxe.googleplaycli.dsl

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.subcommands
import com.github.vacxe.googleplaycli.PlayStoreCli
import com.github.vacxe.googleplaycli.core.BaseCommand
import com.github.vacxe.googleplaycli.core.MetaCommand

fun cmd(name: String, configurationBlock: MetaCommand.() -> Unit): MetaCommand {
    val metaCommand = MetaCommand(name)
    metaCommand.configurationBlock()
    return metaCommand
}

fun MetaCommand.subcmd(name: String, configurationBlock: MetaCommand.() -> Unit) {
    val metaCommand = MetaCommand(name)
    metaCommand.configurationBlock()
    subcommands(metaCommand)
}

fun MetaCommand.baseCmd(name: String, description: String = "", block: BaseCommand.(PlayStoreCli) -> Unit) {
    subcommands(
            object : BaseCommand(name = name, actionDescription = description) {
                override fun run(cli: PlayStoreCli) = block(cli)
            }
    )
}

fun MetaCommand.addCmd(block: () -> CliktCommand) {
    subcommands(block())
}