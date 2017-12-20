package io.javalin.embeddedserver.undertow

import io.javalin.core.JavalinServlet
import io.javalin.embeddedserver.EmbeddedServer
import io.javalin.embeddedserver.EmbeddedServerFactory
import io.javalin.embeddedserver.StaticFileConfig
import io.undertow.Undertow

class EmbeddedUndertowFactory(server: () -> Undertow.Builder = { Undertow.builder() }): EmbeddedServerFactory {
    private val server = server()
    override fun create(javalinServlet: JavalinServlet, staticFileConfig: StaticFileConfig?): EmbeddedServer {
        return EmbeddedUndertowServer(server, javalinServlet.apply { staticResourceHandler = UndertowResourceHandler(staticFileConfig) })
    }
}