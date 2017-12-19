package io.javalin.examples

import io.javalin.Javalin
import io.javalin.embeddedserver.undertow.EmbeddedUndertowFactory

fun main(args: Array<String>) {
    val app = Javalin.create().embeddedServer(EmbeddedUndertowFactory()).port(8080).start()
}