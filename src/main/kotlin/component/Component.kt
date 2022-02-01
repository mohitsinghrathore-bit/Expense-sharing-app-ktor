package component

import controller.ExpenseController
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import repository.ExpenseMysql
import repository.ExpenseRepository

class Component:KoinComponent {
    val Expenserepo:ExpenseRepository by inject<ExpenseMysql>()
    val Expensecontroller:ExpenseController by inject<ExpenseController>()
}