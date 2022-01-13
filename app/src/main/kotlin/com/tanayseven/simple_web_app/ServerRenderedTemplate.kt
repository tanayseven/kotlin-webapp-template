package com.tanayseven.simple_web_app

import io.ktor.application.*
import io.ktor.pebble.*
import io.ktor.response.*
import io.ktor.routing.*

data class User(val id: Int, val name: String)

fun Route.serverTemplateRoutes() {
    get("/rendered-user") {
        val sampleUser = User(1, "John")
        call.respond(PebbleContent("index.html", mapOf("user" to sampleUser)))
    }
}
