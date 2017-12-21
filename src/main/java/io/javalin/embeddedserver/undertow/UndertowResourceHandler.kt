package io.javalin.embeddedserver.undertow

import io.javalin.embeddedserver.CachedRequestWrapper
import io.javalin.embeddedserver.Location
import io.javalin.embeddedserver.StaticFileConfig
import io.javalin.embeddedserver.StaticResourceHandler
import io.undertow.Handlers.resource
import io.undertow.server.HttpServerExchange
import io.undertow.server.handlers.resource.*
import io.undertow.servlet.handlers.ServletHandler
import io.undertow.servlet.spec.HttpServletRequestImpl
import java.io.File
import java.nio.file.Paths
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class UndertowResourceHandler(private val staticFileConfig: StaticFileConfig?) : StaticResourceHandler {
    private var isInitialized = false
    private var basePath = ""
    private var resourceHandler: ResourceHandler? = null
    private val successStatusCodes = listOf(200, 304)
    init {
        if(staticFileConfig != null) {
            basePath = staticFileConfig.path
            isInitialized = true
            if(staticFileConfig.location == Location.CLASSPATH) {
                resourceHandler = resource(ClassPathResourceManager(EmbeddedUndertowServer::class.java.classLoader, File(basePath).path))
            } else if (staticFileConfig.location == Location.EXTERNAL) {
                resourceHandler = resource(PathResourceManager(Paths.get(basePath))).setDirectoryListingEnabled(true)
            }
        }
    }
    override fun handle(httpRequest: HttpServletRequest, httpResponse: HttpServletResponse): Boolean {
        if(isInitialized) {
            val handler = resourceHandler!!
            val exchange = ((httpRequest as CachedRequestWrapper).request as HttpServletRequestImpl).exchange
            handler.handleRequest(exchange)
            val status = exchange.statusCode
            return successStatusCodes.contains(status)
        }
        return false
    }
}