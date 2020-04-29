package com.github.vacxe.googleplaycli.core

fun String.nullIfEmpty(): String? = if (this.isEmpty()) null else this
