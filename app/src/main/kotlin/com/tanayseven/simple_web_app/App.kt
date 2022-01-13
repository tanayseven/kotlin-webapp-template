package com.tanayseven.simple_web_app

import com.mitchellbosecke.pebble.loader.ClasspathLoader
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.pebble.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.serialization.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

// All the Ktor plugins and basic routers here
fun Application.module(testing: Boolean = false) {
    install(ContentNegotiation) {
        json(
            Json {
                prettyPrint = true
                isLenient = true
            }
        )
    }
    install(Routing) {
        customerCrud()
        serverTemplateRoutes()
    }
    routing {
        get("/") {
            call.respond(Health(production = "Healthy"))
        }
    }
    install(Pebble) {
        loader(
            ClasspathLoader().apply {
                prefix = "templates"
            }
        )
    }
}

@Serializable
data class Health(val production: String)
