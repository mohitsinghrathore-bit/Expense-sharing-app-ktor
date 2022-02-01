package database

import database.group.GroupUserdbtable
import database.group.GroupdbTable
import database.user.AuditDbTable
import database.user.Dbauditentitiy
import entities.Group
import entities.InputDraft.GroupDraft
import entities.InputDraft.TransactionDraft
import entities.InputDraft.UserDraft
import entities.User
import org.ktorm.database.Database
import org.ktorm.dsl.*
import org.ktorm.entity.*

class DatabaseManager {
    // config
    private val hostname = "127.0.0.1"
    private val databaseName = "expense_share"
    private val username = "root"
    private val password = "mohit123"

    // database
    private val ktormDatabase: Database

    init {
        val jdbcUrl = "jdbc:mysql://$hostname:3306/$databaseName?user=$username&allowPublicKeyRetrieval=true&password=$password&useSSL=false"
        ktormDatabase = Database.connect(jdbcUrl)
    }
    fun getalluser():List<Dbuserentitiy>{
        return  ktormDatabase.sequenceOf(UserDbTable).toList()
    }
    fun adduser(draft: UserDraft):User{
        val insertid=ktormDatabase.insertAndGenerateKey(UserDbTable){
            set(UserDbTable.contact_no,draft.contact_no)
            set(UserDbTable.name,draft.name)
        }as Int
        val userTable =ktormDatabase.sequenceOf(UserDbTable)
        if(userTable.count()!=0) {
            for (user in userTable) {
                if (user.id == insertid) {
                    continue
                }
                val iid = ktormDatabase.insertAndGenerateKey(AuditDbTable) {
                    set(AuditDbTable.idUser1, insertid)
                    set(AuditDbTable.idUser2, user.id)
                } as Int

                val iid2 = ktormDatabase.insertAndGenerateKey(AuditDbTable) {
                    set(AuditDbTable.idUser1, user.id)
                    set(AuditDbTable.idUser2, insertid)
                } as Int
        } }

        val inserid2=ktormDatabase.insertAndGenerateKey(GroupUserdbtable){
            set(GroupUserdbtable.idGroup,draft.groupid)
            set(GroupUserdbtable.idUser,insertid)
        }as Int

        return User(insertid,draft.name,draft.contact_no)
    }

    fun addTransaction(draft: TransactionDraft):Boolean{
        val GroupUser=ktormDatabase.sequenceOf(GroupUserdbtable)
        val numOfMember=GroupUser.filter { it.idGroup eq draft.groupId }.count()
        val AuditTable=ktormDatabase.sequenceOf(AuditDbTable)
        val amountDist=((draft.Amount)/(numOfMember-1))

            val update=ktormDatabase.update(AuditDbTable){
                set(it.Amount,it.Amount + amountDist)
                where {
                    it.idUser2 eq draft.userId
                }
            }


        return update>0
    }

    fun balanceAtUserlevel(id:Int): EntitySequence<Dbauditentitiy, AuditDbTable> {
        val auditatble=ktormDatabase.sequenceOf(AuditDbTable)
        val aud=auditatble.filter { it.idUser1 eq  id}
        return aud
    }
    fun balanceAtGrouplevel(id:Int): EntitySequence<Dbauditentitiy, AuditDbTable> {
        val auditatble=ktormDatabase.sequenceOf(AuditDbTable)
        val groupuserTable=ktormDatabase.sequenceOf(GroupUserdbtable)
        val groupuser=groupuserTable.filter { it.idGroup eq id }.map { it.idUser }
        val aud=auditatble.filter { it.idUser1 inList    groupuser }.filter { it.idUser2 inList groupuser }
        return aud
    }
    fun addgroup(draft:GroupDraft): Group {
        val insertid=ktormDatabase.insertAndGenerateKey(GroupdbTable){
            set(GroupdbTable.name,draft.name)
        }as Int
        return Group(insertid,draft.name)
    }


}