package io.javalin.embeddedserver.undertow

import io.javalin.core.JavalinServlet
import io.javalin.embeddedserver.EmbeddedServer
import io.javalin.embeddedserver.EmbeddedServerFactory
import io.javalin.embeddedserver.StaticFileConfig

class EmbeddedUndertowFactory: EmbeddedServerFactory {
    override fun create(javalinServlet: JavalinServlet, staticFileConfig: StaticFileConfig?): EmbeddedServer {
        return EmbeddedUndertowServer()
    }
}