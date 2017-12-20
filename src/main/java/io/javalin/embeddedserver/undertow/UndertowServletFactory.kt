package io.javalin.embeddedserver.undertow

import io.javalin.core.JavalinServlet
import io.undertow.servlet.api.InstanceFactory
import io.undertow.servlet.api.InstanceHandle
import io.undertow.servlet.util.ImmediateInstanceHandle

class UndertowServletFactory(private val javalinServlet: JavalinServlet): InstanceFactory<EmbeddedUndertowServlet> {
    override fun createInstance(): InstanceHandle<EmbeddedUndertowServlet> {
        return ImmediateInstanceHandle<EmbeddedUndertowServlet>(EmbeddedUndertowServlet(javalinServlet))
    }
}