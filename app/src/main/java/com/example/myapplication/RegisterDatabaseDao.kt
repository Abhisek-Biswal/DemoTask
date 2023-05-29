package com.example.myapplication

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface RegisterDatabaseDao {
@Insert
 suspend fun  insert(register : RegisterEntity)


 @Query("SELECT * FROM Register_task_table")
 suspend fun getAllTask(): List<RegisterEntity>

 @Insert
 suspend fun  insertEntity(entity: RegisterEntity)
 @Update
 suspend fun  updateentity(entity: RegisterEntity)
 @Delete
 suspend fun  deleteentity(entity: RegisterEntity)
















//    @Query("SELECT * FROM Register_task_table")
//    suspend fun getAllTask(): List<RegisterEntity>
//
//    @Update
//    suspend fun updateUserDetails(register:RegisterEntity )
//    //@Query("DELETE FROM Register_users_table WHERE id = :id")
//   // suspend fun deleteSingleUserDetails(id: Int)
//
//    //delete all user details
//    @Delete
//    suspend fun deleteAllUsersDetails(register: RegisterEntity)
}