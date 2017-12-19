package io.javalin.embeddedserver.undertow

import io.javalin.core.JavalinServlet
import io.javalin.embeddedserver.EmbeddedServer
import io.undertow.servlet.api.DeploymentInfo

class EmbeddedUndertowServer(javalinServlet: JavalinServlet, deploymentInfo: DeploymentInfo): EmbeddedServer {
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