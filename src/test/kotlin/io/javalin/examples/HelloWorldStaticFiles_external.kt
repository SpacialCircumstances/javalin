/*
 * Javalin - https://javalin.io
 * Copyright 2017 David Åse
 * Licensed under Apache 2.0: https://github.com/tipsy/javalin/blob/master/LICENSE
 */

package io.javalin.examples

import io.javalin.Javalin
import io.javalin.embeddedserver.Location
import io.javalin.embeddedserver.undertow.EmbeddedUndertowFactory

fun main(args: Array<String>) {
    Javalin.create()
            .embeddedServer(EmbeddedUndertowFactory())
            .port(7070)
            .enableStaticFiles("src/test/external/", Location.EXTERNAL)
            .start()
}
