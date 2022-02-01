package database.group


import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object GroupUserdbtable: Table<Dbgrusentitiy>("Group-User"){
    val id=int("id").primaryKey().bindTo { it.id  }
    var idUser=int("idUser").bindTo {  it.idUser}
    var idGroup=int("idGroup").bindTo {  it.idGroup}
}

interface Dbgrusentitiy: Entity<Dbgrusentitiy> {
    companion object: Entity.Factory<Dbgrusentitiy>()
    val id:Int
    val idUser:Int
    var idGroup:Int
}