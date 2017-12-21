package io.javalin.embeddedserver.undertow

import io.javalin.core.JavalinServlet
import io.javalin.embeddedserver.EmbeddedServer
import io.javalin.embeddedserver.EmbeddedServerFactory
import io.javalin.embeddedserver.StaticFileConfig
import io.undertow.Undertow
import io.undertow.servlet.Servlets

class EmbeddedUndertowFactory: EmbeddedServerFactory {
    override fun create(javalinServlet: JavalinServlet, staticFileConfig: StaticFileConfig?): EmbeddedServer {
        return EmbeddedUndertowServer({ port: Int, jservlet: JavalinServlet -> buildServer(port, jservlet)}, javalinServlet)
    }

    private fun buildServer(port: Int, javalinServlet: JavalinServlet): Undertow {
        val deployment = Servlets.deployment().setClassLoader(EmbeddedUndertowServer::class.java.classLoader)
        deployment.contextPath = "/"
        deployment.deploymentName = "javalin"
        deployment.addServlets(Servlets.servlet("javalinServlet", EmbeddedUndertowServlet::class.java, UndertowServletFactory(javalinServlet)).addMapping("/"))
        val deploymentManager = Servlets.defaultContainer().addDeployment(deployment)
        deploymentManager.deploy()
        return Undertow.builder().addHttpListener(port, "localhost").setHandler(deploymentManager.start()).build()
    }
}