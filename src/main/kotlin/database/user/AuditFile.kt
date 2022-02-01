package database.user

import database.UserDbTable.bindTo
import database.UserDbTable.primaryKey
import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object AuditDbTable: Table<Dbauditentitiy>("AuditTable"){
    val id=int("id").primaryKey().bindTo { it.id  }
    var idUser1=int("idUser1").bindTo {  it.idUser1}
    var idUser2=int("idUser2").bindTo { it.idUser2 }
    var Amount=int("Amount").bindTo { it.Amount }
}

interface Dbauditentitiy: Entity<Dbauditentitiy> {
    companion object: Entity.Factory<Dbauditentitiy>()
    val id:Int
    var idUser1:Int
    var idUser2:Int
    var Amount:Int
}