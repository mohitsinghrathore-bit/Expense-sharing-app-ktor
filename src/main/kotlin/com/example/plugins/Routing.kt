package com.example.plugins

import component.Component
import controller.ExpenseController
import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.serialization.gson.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.response.*

fun Application.configureRouting() {
    install(ContentNegotiation) {
        gson {

        }
    }
    routing {
        val comp=Component()
        val controller=comp.Expensecontroller
        get("/") {
            call.respondText("Hello World!")
        }
        get("/user"){
            controller.getalluser(call)
        }
        post("/user"){
            controller.adduser(call)
        }
        put("/txn"){
            controller.addTransaction(call)
        }
        get("/user/{id}"){
            controller.balanceAtUserlevel(call)
        }
        get("/grp/{id}"){
            controller.balanceAtGroupLevel(call)
        }
        post("/grp"){
            controller.addgroup(call)
        }
    }
}
