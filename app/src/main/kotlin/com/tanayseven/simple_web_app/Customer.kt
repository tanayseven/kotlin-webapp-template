package com.tanayseven.simple_web_app

import arrow.core.Validated
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import java.io.File

fun Route.customerCrud() {
    get("/customer") {
        call.respond(message = CustomerDataStore.customers)
    }
    post("/customer/new") {
        val customer = call.receive<CreateCustomerRequest>()
        when (val validatedCustomer = customer.validate()) {
            is Validated.Invalid -> {
                call.response.status(HttpStatusCode.BadRequest)
                call.respond(message = validatedCustomer.value)
            }
            is Validated.Valid -> {
                // TODO add code to create the customer
                call.response.status(HttpStatusCode.Created)
                call.respond(message = CreateCustomerResponse(username = validatedCustomer.value.username))
            }
        }
    }
}

@Serializable
data class Error(val path: String, val message: String)

@Serializable
data class CreateCustomerRequest(
    val username: String,
    val email: String,
    val phone: String,
)

fun CreateCustomerRequest.validate(): Validated<List<Error>, CreateCustomerRequest> {
    var errors = listOf<Error>()
    if (!this.email.contains("@"))
        errors = errors + listOf(Error(path = "email", message = "the email is in invalid format"))
    if (errors.isNotEmpty())
        return Validated.Invalid(errors)
    return Validated.Valid(this)
}

@Serializable
data class CreateCustomerResponse(
    val username: String,
)

@Serializable
data class Customer(val id: String, val username: String, val email: String, val phone: String)

object CustomerDataStore {
    private val db = File(ClassLoader.getSystemResource("db.json").file).readText()
    val customers = Json.decodeFromString(
        ListSerializer(Customer.serializer()), db
    ).toMutableList()
    fun addCustomer(customer: Customer) = customers.add(customer)
}
