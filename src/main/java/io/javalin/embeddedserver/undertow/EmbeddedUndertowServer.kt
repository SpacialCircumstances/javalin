package io.javalin.embeddedserver.undertow

import io.javalin.core.JavalinServlet
import io.javalin.embeddedserver.EmbeddedServer
import io.undertow.Undertow
import io.undertow.servlet.Servlets
import io.undertow.servlet.api.InstanceFactory
import io.undertow.servlet.api.InstanceHandle
import io.undertow.servlet.util.ImmediateInstanceHandle
import java.net.BindException
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class EmbeddedUndertowServer(private val serverBuilder: (port: Int, jservlet: JavalinServlet) -> Undertow, private val javalinServlet: JavalinServlet) : EmbeddedServer {
    lateinit var server: Undertow
    override fun start(port: Int): Int {
        var success = false
        var retryPort = 8080
        while(!success) {
            val actualPort = if (port != 0) port else retryPort
            try {
                server = serverBuilder(actualPort, javalinServlet)
                server.start()
                success = true
                return actualPort
            } catch (e: java.lang.RuntimeException) {
                retryPort++
            }
        }
        return 0
    }

    override fun stop() {
        server.stop()
    }

    override fun activeThreadCount(): Int {
        return 0
    }

    override fun attribute(key: String): Any {
        return 0
    }
}