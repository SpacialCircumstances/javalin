package io.javalin.embeddedserver.undertow

import io.javalin.core.JavalinServlet
import io.javalin.embeddedserver.EmbeddedServer
import io.undertow.Undertow
import io.undertow.servlet.Servlets
import io.undertow.servlet.api.InstanceFactory
import io.undertow.servlet.api.InstanceHandle
import io.undertow.servlet.util.ImmediateInstanceHandle
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class EmbeddedUndertowServer(private val serverBuilder: Undertow.Builder, private final val javalinServlet: JavalinServlet) : EmbeddedServer {
    lateinit var server: Undertow
    override fun start(port: Int): Int {
        val deployment = Servlets.deployment().setClassLoader(EmbeddedUndertowServer::class.java.classLoader)
        deployment.contextPath = "/"
        deployment.deploymentName = "javalin"
        deployment.addServlets(Servlets.servlet("javalinServlet", EmbeddedUndertowServlet::class.java, UndertowServletFactory(javalinServlet)).addMapping("/"))
        val deploymentManager = Servlets.defaultContainer().addDeployment(deployment)
        deploymentManager.deploy()
        server = serverBuilder.addHttpListener(port, "localhost").setHandler(deploymentManager.start()).build()
        server.start()
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