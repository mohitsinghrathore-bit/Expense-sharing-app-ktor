package database

import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.boolean
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object UserDbTable: Table<Dbuserentitiy>("User"){
    val id=int("id").primaryKey().bindTo { it.id  }
    var name=varchar("name").bindTo {  it.name}
    var contact_no=varchar("contact_no").bindTo { it.contact_no }
}

interface Dbuserentitiy: Entity<Dbuserentitiy> {
    companion object: Entity.Factory<Dbuserentitiy>()
    val id:Int
    var name:String
    var contact_no:String
}