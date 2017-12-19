package io.javalin.embeddedserver.undertow

import io.javalin.embeddedserver.EmbeddedServer

class EmbeddedUndertowServer: EmbeddedServer {
    override fun start(port: Int): Int {
        return 0
    }

    override fun stop() {

    }

    override fun activeThreadCount(): Int {
        return 0
    }

    override fun attribute(key: String): Any {
        return 0
    }
}