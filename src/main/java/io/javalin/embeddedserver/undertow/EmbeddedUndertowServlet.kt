package io.javalin.embeddedserver.undertow

import io.javalin.core.JavalinServlet
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServlet

class EmbeddedUndertowServlet(private val javalinServlet: JavalinServlet): HttpServlet() {
    override fun service(req: ServletRequest?, res: ServletResponse?) {
        javalinServlet.service(req!!, res!!)
    }
}