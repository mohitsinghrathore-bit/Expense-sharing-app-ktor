package controller

import entities.InputDraft.TransactionDraft
import entities.InputDraft.UserDraft
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import repository.ExpenseMysql
import repository.ExpenseRepository

class ExpenseController(val Repos:ExpenseRepository) {
      suspend fun getalluser(call: ApplicationCall){
          call.respond(Repos.getalluser())
      }
      suspend fun adduser(call: ApplicationCall){
            val draft=call.receive<UserDraft>()
            call.respond(Repos.adduser(draft))
      }
      suspend fun addTransaction(call: ApplicationCall){
            val draft=call.receive<TransactionDraft>()
            val Succ=Repos.addTransaction(draft)
            if(Succ){
                  call.respond(HttpStatusCode.OK)
            }else{
                  call.respond(HttpStatusCode.BadRequest,"Check The Input")
            }
      }
      suspend fun balanceAtUserlevel(call: ApplicationCall){
            val id = call.parameters["id"]?.toIntOrNull()
            if(id==null){
                  call.respond(HttpStatusCode.BadRequest)
                  return
            }
            call.respond(Repos.balanceAtUserlevel(id))
      }
      suspend fun balanceAtGroupLevel(call: ApplicationCall){
            val id = call.parameters["id"]?.toIntOrNull()
            if(id==null){
                  call.respond(HttpStatusCode.BadRequest)
                  return
            }
            call.respond(Repos.balanceAtGrouplevel(id))
      }
}