package di

import controller.ExpenseController
import org.koin.dsl.bind
import org.koin.dsl.module
import repository.ExpenseMysql
import repository.ExpenseRepository

val  ExpenseModule = module {
    factory { ExpenseMysql() } bind ExpenseRepository :: class
    factory { ExpenseController(get()) }
}