package com.tanayseven.simple_web_app

import io.kotest.assertions.ktor.shouldHaveStatus
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlinx.serialization.json.Json.Default.decodeFromString

// tests for HTTP endpoints
class AppTest : StringSpec({
    "application is healthy" {
        withTestApplication(Application::module) {
            handleRequest(HttpMethod.Get, "/").apply {
                val jsonResponseBody = decodeFromString(Health.serializer(), response.content!!)
                response shouldHaveStatus HttpStatusCode.OK
                jsonResponseBody.production shouldBe "Healthy"
            }
        }
    }
})
