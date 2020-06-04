package com.github.vacxe.googleplaycli.dsl

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.subcommands
import com.github.vacxe.googleplaycli.core.MetaCommand

fun cmd(name: String, configurationBlock: MetaCommand.() -> Unit): MetaCommand = MetaCommand(name).apply(configurationBlock)

fun MetaCommand.subcmd(name: String, configurationBlock: MetaCommand.() -> Unit) {
    subcommands(cmd(name, configurationBlock))
}

fun MetaCommand.addCmd(block: () -> CliktCommand) {
    subcommands(block())
}