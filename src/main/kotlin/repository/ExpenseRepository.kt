package repository

import entities.AuditTable
import entities.Group
import entities.InputDraft.GroupDraft
import entities.InputDraft.TransactionDraft
import entities.InputDraft.UserDraft
import entities.User
import org.ktorm.database.Database

interface ExpenseRepository {
    fun getalluser():List<User>

    fun adduser(draft: UserDraft):User

    fun addTransaction(draft: TransactionDraft):Boolean

    fun balanceAtUserlevel(id:Int):List<AuditTable>

    fun balanceAtGrouplevel(id:Int):List<AuditTable>

    fun addgroup(draft: GroupDraft):Group
}