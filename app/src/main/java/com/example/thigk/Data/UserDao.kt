package com.example.thigk.Data
//UserDao chứa tất cả các phương thức được sd để truy cập csdl
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.thigk.Model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("DELETE FROM user_Table")
    suspend fun deleteAllUsers()

    @Query("SELECT * FROM user_Table ORDER BY id ASC")
    fun readAllData(): LiveData<List<User>>

//    @Query("SELECT * FROM userTable WHERE studentName LIKE :searchQuery OR className LIKE :searchQuery OR age LIKE :searchQuery")
//    fun searchDatabase(searchQuery: String): Flow<List<User>>
}