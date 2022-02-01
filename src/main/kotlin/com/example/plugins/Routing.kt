package com.example.plugins

import component.Component
import controller.ExpenseController
import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.serialization.gson.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.response.*
import io.ktor.server.request.*

fun Application.configureRouting() {
    install(ContentNegotiation) {
        gson {

        }
    }
    routing {
        val comp=Component()
        val Controller=comp.Expensecontroller
        get("/") {
            call.respondText("Hello World!")
        }
        get("/user"){
            Controller.getalluser(call)
        }
        post("/user"){
            Controller.adduser(call)
        }
        put("/txn"){
            Controller.addTransaction(call)
        }
        get("/user/{id}"){
            Controller.balanceAtUserlevel(call)
        }
        get("/grp/{id}"){
            Controller.balanceAtGroupLevel(call)
        }
        post("/grp"){
            Controller.addgroup(call)
        }
    }
}
