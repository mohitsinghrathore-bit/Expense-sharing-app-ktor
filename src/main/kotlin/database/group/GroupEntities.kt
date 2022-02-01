package database.group

import database.UserDbTable.bindTo
import database.UserDbTable.primaryKey
import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object GroupdbTable: Table<Dbgroupentitiy>("Group"){
    val id=int("id").primaryKey().bindTo { it.id  }
    var name=varchar("name").bindTo {  it.name}
}

interface Dbgroupentitiy: Entity<Dbgroupentitiy> {
    companion object: Entity.Factory<Dbgroupentitiy>()
    val id:Int
    var name:String
}