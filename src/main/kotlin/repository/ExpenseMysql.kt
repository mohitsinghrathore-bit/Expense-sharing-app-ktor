package repository

import database.DatabaseManager
import entities.AuditTable
import entities.Group
import entities.InputDraft.GroupDraft
import entities.InputDraft.TransactionDraft
import entities.InputDraft.UserDraft
import entities.User
import org.ktorm.database.Database
import org.ktorm.entity.map

class ExpenseMysql: ExpenseRepository {
    private val Database= DatabaseManager()
    override fun getalluser(): List<User> {
         return Database.getalluser().map { User(it.id,it.name,it.contact_no ) }
    }

    override fun adduser(draft: UserDraft): User {
        return Database.adduser(draft)

    }

    override fun addTransaction(draft: TransactionDraft):Boolean {
        return Database.addTransaction(draft)
    }

    override fun balanceAtUserlevel(id: Int):List<AuditTable>{
       return  Database.balanceAtUserlevel(id).map { AuditTable(it.idUser1,it.idUser2,it.Amount) }
    }

    override fun balanceAtGrouplevel(id: Int): List<AuditTable> {
        return Database.balanceAtGrouplevel(id).map { AuditTable(it.idUser1,it.idUser2,it.Amount) }
    }

    override fun addgroup(draft: GroupDraft): Group {
        return Database.addgroup(draft)
    }


}